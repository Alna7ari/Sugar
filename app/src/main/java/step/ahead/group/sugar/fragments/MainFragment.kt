package step.ahead.group.sugar.fragments

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main_fargment.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.dialogs.AddResultDialog
import step.ahead.group.sugar.handlers.DrugHandler
import step.ahead.group.sugar.handlers.TestResultHandler
import step.ahead.group.sugar.handlers.UserInfoHandler
import step.ahead.group.sugar.libraries.sse.EventHandler
import step.ahead.group.sugar.libraries.sse.EventSource
import step.ahead.group.sugar.libraries.sse.MessageEvent
import step.ahead.group.sugar.models.TestResult
import step.ahead.group.sugar.utils.BroadcastUtil
import step.ahead.group.sugar.utils.StringUtils
import step.ahead.group.sugar.utils.ToastUtil
import step.ahead.group.sugar.webservices.Urls
import step.ahead.group.sugar.webservices.WebService
import java.math.RoundingMode


class MainFragment : Fragment() {

    private val className = javaClass.name
    private var savedResult = TestResult()
    private var isDisconnected = false
    private val header: Map<String, String> =
        mapOf(Pair("Authorization", "Basic QWhtZWQ6SGFnYXJLaGFkaWdhWm9sZmFaZWlhZA=="))
    private val url = Urls.MAIN_URL + "events"
    val funMap = hashMapOf(
        "onConnected" to this::onConnected,
        "onStripDisconnected" to this::onStripDisconnected,
        "onStripConnected" to this::onStripConnected,
        "onStartCheck" to this::onStartCheck,
        "onErrorResult" to this::onErrorResult,
        "onSuccessResult" to this::onSuccessResult
    )
    private val eventSource: EventSource = EventSource(url, header, object : EventHandler {
        override fun onOpen() {
            Log.d(className, "onOpen")
        }

        override fun onMessage(event: MessageEvent) {
            val method = event.event
            val data = event.data
            try {
            activity!!.runOnUiThread {

                    this@MainFragment.funMap[method]?.call(data)

            }
            } catch (e: Exception) {
            }
        }

        override fun onError(e: Exception?) {
            if (isDisconnected) return
            try {
                activity!!.runOnUiThread {

                    onDisconnected(e?.message)
                    isDisconnected = true

                }
            } catch (e: Exception) {
            }
        }
    });
    private val notificationActionsReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val message = intent.getStringExtra("message") ?: return
            val from = intent.getStringExtra("from")
            if (from == BroadcastUtil.FROM_ESP) {
                // تجربة
            }
            if (from == BroadcastUtil.FROM_SMS) {
                // ىوى
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main_fargment, container, false)
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        save_btn.setOnClickListener {
            saveResult()
        }
        cancel_btn.setOnClickListener {
            result_textveiw.text = "0.0"
            savedResult = TestResult()
        }
        add_result_btn.setOnClickListener {
            // اين ال layout حق الاضافة؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟
            AddResultDialog().show(requireActivity().supportFragmentManager, "addResult")
        }
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        try {
            LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
                notificationActionsReceiver,
                IntentFilter(BroadcastUtil.TEST_RESULT_RECEIVED_ACTION)
            )
            welcome_text.text = "مرحباً: " + UserInfoHandler.getInstance().userInfo?.firstName
            result_textveiw.text = TestResultHandler.getInstance().getLast.result.toString()
            //asyncCheck()
            eventSource.connect()
        } catch (e: Exception) {
        }

    }

    fun onConnected(data: String) {
        isDisconnected = false
        Log.d(className, "onConnected: $data")
        waiting_result_text.text = "تم الاتصال بنجاح!"
    }

    fun onDisconnected(error: String?) {
        Log.d(className, "onConnected: $error")
        waiting_result_text.text = "في انتظار الاتصال بالجهاز"
    }

    fun onStripDisconnected(data: String) {
        Log.d(className, "onStripDisconnected: $data")
        waiting_result_text.text = "تم فصل الشريحة!"
    }

    fun onStripConnected(data: String) {
        waiting_result_text.text = "تم تركيب الشريحة بنجاح, في انتظار وضع عينة الدم الان!"
        Log.d(className, "onStripConnected: $data")
    }

    fun onStartCheck(data: String) {
        Log.d(className, "onStartCheck: $data")
        waiting_result_text.text = "تم وضع الدم, يتم الان الفحص, ستضهر النتيجة خلال لحظات...!"
    }

    fun onErrorResult(data: String) {
        Log.d(className, "onErrorResult: $data")
        waiting_result_text.text =
            if (data == "ERR") "شريحة الفحص غير صالحة" else "قراءة غير صحيحة!"
        val rounded = try {
            data.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        } catch (e: Exception) {
            StringUtils.format(data)
        }
        result_textveiw.text = rounded.toString()
    }

    fun onSuccessResult(data: String) {
        Log.d(className, "onSuccessResult: $data")
        waiting_result_text.text = "تمت القراءة بنجاح!"
        val rounded = try {
            data.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        } catch (e: Exception) {
            StringUtils.format(data)
        }
        result_textveiw.text = rounded.toString()
    }

    private fun saveResult() {
        val result = result_textveiw.text.toString().toDoubleOrNull()
        if (result == null || result < 10) {
            Toast.makeText(context, "لايمكن حفظ قيمة غير صحيحة!", Toast.LENGTH_SHORT).show()
            return
        }
        if (savedResult.result == result) {

            ToastUtil(context, "لايمكن حفظ نفس القيمة مرتين!!")
            return
        }
        val testResult = TestResult()
        testResult.result = result
        testResult.createdAt = System.currentTimeMillis() / 1000
        savedResult = testResult
        TestResultHandler.getInstance().save(testResult)
        Toast.makeText(context, "تم الحفظ بنجاح!!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        try {
            LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(
                notificationActionsReceiver
            )
            eventSource.close()
        } catch (e: Exception) {
        }
        super.onDestroy()
    }
}