package step.ahead.group.sugar.webservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.gson.Gson
import step.ahead.group.sugar.libraries.sse.EventHandler
import step.ahead.group.sugar.libraries.sse.EventSource
import step.ahead.group.sugar.libraries.sse.MessageEvent
import step.ahead.group.sugar.requests.ResultDataRequest
import java.net.URI
import java.util.HashMap


class EventSourceService : Service() {

    private val className = javaClass.name


    override fun onCreate() {
        super.onCreate()

    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented")
    }


}