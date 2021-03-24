package step.ahead.group.sugar.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import step.ahead.group.sugar.R
import step.ahead.group.sugar.utils.BroadcastUtil


class MainFragment : Fragment() {

    private val notificationActionsReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val message = intent.getStringExtra("message") ?: return
            val from = intent.getStringExtra("from")
            if (from == BroadcastUtil.FROM_ESP) {

            }
            if (from == BroadcastUtil.FROM_SMS) {

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

    override fun onResume() {


        super.onResume()
        try {
            LocalBroadcastManager.getInstance(requireContext()).registerReceiver(notificationActionsReceiver,
                IntentFilter(BroadcastUtil.TEST_RESULT_RECEIVED_ACTION)
            )
        }catch (e: Exception){}

    }

    override fun onDestroy() {
        try {
            LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(notificationActionsReceiver)
        }catch (e: Exception){}
        super.onDestroy()
    }
}