package step.ahead.group.sugar.listeners

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import step.ahead.group.sugar.utils.BroadcastUtil


class SmsListeners : BroadcastReceiver() {
    // جملة او كلمة موجودة في رسالة الفحص الذي ستصل, اذا لم يجد هذة الكلمة في الرسالة لن يتم اعتبارها رساله للفحص وسيتم اعتبارها رسالة عادية وتجاوزها
    private val messageKeyWord = "Glucose level:" // or (mg/dl)
    override fun onReceive(context: Context?, intent: Intent?) {
        if (!intent?.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) return

        var messageFrom: String? = null
        var messagesBody = ""
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Telephony.Sms.Intents.getMessagesFromIntent(intent).forEach {
                messageFrom = it.originatingAddress;
                messagesBody += it.messageBody;
            }
        } else {
            val bundle = intent?.extras ?: return
            val data = bundle["pdus"] as Array<*>? ?: return
            data.forEach {
                @Suppress("DEPRECATION")
                val message = SmsMessage.createFromPdu(it as ByteArray) ?: return
                messageFrom = message.originatingAddress;
                messagesBody += message.messageBody;
            }
        }
        Log.d(javaClass.name, messagesBody)
        if (! messagesBody.contains(messageKeyWord)) return
        Log.d(javaClass.name, "done")
        // من هنا نبدا بمعالجة الرسالة واخذ السطر المطلوب ثم عمل حدث بها ليستقبلها
        val result = messagesBody.substringAfter("level:").substringBefore("mg/dl").replace(" ", "").toDoubleOrNull() ?: return
        Log.d(javaClass.name, "result: $messagesBody")
        Log.d(javaClass.name, "result: $result")
        BroadcastUtil.sendEvent(context, result.toString())
        
    }
}