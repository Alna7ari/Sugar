package step.ahead.group.sugar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.RealmList
import step.ahead.group.sugar.R
import step.ahead.group.sugar.models.TestResult


class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.notifications_frament, container, false)
    }

    fun showReports(reports: RealmList<TestResult>) {

    }
}