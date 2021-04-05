package step.ahead.group.sugar.libraries.sse

import android.os.Build
import android.util.Log
//import com.alna7ari.fawrysim.webservices.Tls12SocketFactory
import okhttp3.*
import okio.BufferedSource
import java.io.IOException
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext


class EventSource(
    private val url: String,
    private val header: Map<String, String>?,
    private val eventHandler: EventHandler
) {
    val readTimeout: Long = 0
    var readyState = CLOSED
        private set
    var lastEventId = ""
        private set
    var reconnectionTime: Long = 2000
        private set
    private var call: Call? = null
    private var threadRetry: Thread? = null

    constructor(
        url: String,
        eventHandler: EventHandler
    ) : this(url, null, eventHandler) {
    }

    fun connect() {
        close()
        readyState = CONNECTING
        Executors.newSingleThreadExecutor()
            .submit { connectInternal() }
    }

    fun close() {
        if (threadRetry != null) {
            threadRetry!!.interrupt()
        }
        if (call != null) {
            call!!.cancel()
        }
        readyState = CLOSED
    }

    private fun connectInternal() {
        readyState = CONNECTING
        val client = getNewHttpClient()

        val builder = Request.Builder()
            .url(url)
            .addHeader("Accept", "text/event-stream")
        if (lastEventId.isNotEmpty()) {
            builder.addHeader("Last-Event-ID", lastEventId)
        }
        if (header != null) {
            for ((key, value) in header) {
                builder.addHeader(key, value)
            }
        }
        call = client.newCall(builder.build())
        call!!.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                notifyError(e)
            }

            @Throws(IOException::class)
            override fun onResponse(
                call: Call,
                response: Response?
            ) {
                if (response == null) {
                    notifyError(IOException())
                    return
                }

                if (response.isSuccessful) {
                    readyState = OPEN
                    notifyOpen()
                    read(response)
                    return
                }
                notifyError(IOException(response.message()))
            }
        })
    }

    private fun read(response: Response) {
        if (response.body() == null) {
            return
        }
        val source = response.body()!!.source() ?: return
        val reader = EventSourceReader(source)
        reader.read()
    }

    private fun notifyOpen() {
        eventHandler.onOpen()
    }

    private fun notifyMessage(event: MessageEvent) {
        eventHandler.onMessage(event)
    }

    private fun notifyError(e: Exception?) {
        /*if (readyState == CLOSED) {
            return
        }*/
        eventHandler.onError(e)
        readyState = CONNECTING
        retry()
        /*if (call != null && !call!!.isCanceled) {
            readyState = CONNECTING
            retry()
        } else {
            readyState = CLOSED
            call!!.cancel()
        }*/
    }

    private fun retry() {
        try {
            if (call != null) {
                call!!.cancel()
            }
            threadRetry = Thread.currentThread()
            Thread.sleep(reconnectionTime)
            connectInternal()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    internal inner class EventSourceReader(var source: BufferedSource) {
        var event: String? = null
        var id: String? = null
        var data: MutableList<String> =
            ArrayList()

        fun read() {
            clear()
            if (call == null) {
                return
            }
            try {
                while (!call!!.isCanceled) {
                    val line = source.readUtf8LineStrict()
                    if (line.isEmpty()) {
                        dispatchEvent()
                    } else {
                        parse(line)
                    }
                }
            } catch (e: IOException) {
                notifyError(e)
            } finally {
                try {
                    source.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        fun clear() {
            event = null
            id = null
            data.clear()
        }

        fun parse(line: String) {
            var field = line
            var value = ""
            val index = line.indexOf(":")
            if (index == 0) {
                // comment
                return
            } else if (index != -1) {
                field = line.substring(0, index)
                value = line.substring(index + 1).trim { it <= ' ' }
            }
            if ("event" == field) {
                event = value
            } else if ("data" == field) {
                data.add(value)
            } else if ("id" == field) {
                id = value
            } else if ("retry" == field) {
                setRetry(value)
            }
        }

        fun dispatchEvent() {
            if (data.isEmpty()) {
                return
            }
            val event = MessageEvent(event, id, joinData())
            if (id != null) {
                lastEventId = id!!
            }
            notifyMessage(event)
            clear()
        }

        fun joinData(): String {
            val builder = StringBuilder()
            var i = 0
            val n = data.size
            while (i < n) {
                if (i > 0) {
                    builder.append("\n")
                }
                builder.append(data[i])
                i++
            }
            return builder.toString()
        }

        fun setRetry(value: String) {
            try {
                reconnectionTime = value.toLong()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
        }

    }

    private fun enableTls12OnPreLollipop(client: OkHttpClient.Builder): OkHttpClient.Builder {
        if (Build.VERSION.SDK_INT in 16..21) {
            try {
                val sc: SSLContext = SSLContext.getInstance("TLSv1.2")
                sc.init(null, null, null)
                //client.sslSocketFactory(Tls12SocketFactory(sc.socketFactory))
                val cs = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2).build()
                val specs: MutableList<ConnectionSpec> = ArrayList()
                specs.add(cs)
                specs.add(ConnectionSpec.COMPATIBLE_TLS)
                specs.add(ConnectionSpec.CLEARTEXT)
                client.connectionSpecs(specs)
            } catch (exc: java.lang.Exception) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc)
            }
        }
        return client
    }

    private fun getNewHttpClient(): OkHttpClient {
        val client =
            OkHttpClient.Builder()
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
        return enableTls12OnPreLollipop(client).build()
    }

    companion object {
        const val CONNECTING = 0
        const val OPEN = 1
        const val CLOSED = 2
    }

}