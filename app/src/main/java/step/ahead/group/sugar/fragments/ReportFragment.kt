package step.ahead.group.sugar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_reports_fragment.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.adapters.ReportAdapter
import step.ahead.group.sugar.dialogs.ShareDialog
import step.ahead.group.sugar.handlers.TestResultHandler
import step.ahead.group.sugar.models.TestResult
import step.ahead.group.sugar.utils.ToastUtil


class ReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_reports_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        share_btn.setOnClickListener {
            ShareDialog().show(requireActivity().supportFragmentManager, "share")
        }
        super.onViewCreated(view, savedInstanceState)
        val reports = TestResultHandler.getInstance().getAll
        if (reports.isNullOrEmpty()) {
            ToastUtil(context, "لايوجد اي تقارير!");
            return
        }
        showReports(reports)
    }
    private fun showReports(reports: RealmResults<TestResult>) {
        try {
            recyclar_view_reports!!.layoutManager =
                LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        } catch (ex: KotlinNullPointerException) { return }

        val adapter = ReportAdapter(reports){

        }
        recyclar_view_reports.adapter = adapter
    }
}