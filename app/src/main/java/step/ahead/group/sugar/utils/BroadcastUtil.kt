package step.ahead.group.sugar.utils

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager

object BroadcastUtil {
    const val TEST_RESULT_RECEIVED_ACTION = "test_result"
    const val FROM_ESP = "esp"
    const val FROM_SMS = "sms"
    fun sendEvent(context: Context?, message: String?, from: String = FROM_SMS, action: String = TEST_RESULT_RECEIVED_ACTION) {
        if (context == null || message == null) return
        val intent = Intent(action)
        intent.putExtra("from", from)
        intent.putExtra("message", message)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }
}