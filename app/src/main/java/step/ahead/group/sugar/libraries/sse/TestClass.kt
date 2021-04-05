package step.ahead.group.sugar.libraries.sse

import step.ahead.group.sugar.webservices.Urls
import java.util.*

class TestClass {
    private val header: Map<String, String> = HashMap()
    private var url = Urls.MAIN_URL + "events"
    init {
        val token = "QWhtZWQ6SGFnYXJLaGFkaWdhWm9sZmFaZWlhZA=="
        header.plus(Pair("Authorization", token))
    }
    var eventSource = EventSource(url, header, object : EventHandler {
        override fun onOpen() {
            // run on worker thread
            //Log.d(TAG, "Open");
        }

        override fun onMessage(messageEvent: MessageEvent) {
            // run on worker thread
            //Log.d(TAG, "Message");
        }

        override fun onError(e: Exception?) {
            // run on worker thread
            //Log.w(TAG, e);
        }
    })

    fun function() {}
}