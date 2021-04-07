package step.ahead.group.sugar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.my_doctor_fragment.*
import kotlinx.android.synthetic.main.my_doctor_fragment.add_btn
import kotlinx.android.synthetic.main.my_doctor_fragment.close_btn
import kotlinx.android.synthetic.main.my_drags_fragment.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.adapters.DoctorAdapter
import step.ahead.group.sugar.dialogs.AddDrugDialog
import step.ahead.group.sugar.handlers.DoctorHandler
import step.ahead.group.sugar.utils.FragmentUtils


class DoctorFragment : MasterStuffFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.my_doctor_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        // كود الرجوع لصفحة المزيد
        // ركز على ايدي زر الخروج ووحدة في كل الصفحات افضل
        // من هنا ابدا النسخ
        close_btn.setOnClickListener {
            FragmentUtils().open(activity, MoreFragment())
        }

        add_btn.setOnClickListener {
            AddDrugDialog().show(requireActivity().supportFragmentManager, "drug")
        }
        //show()
    }
    private fun show() {
        try {
            recycler_view!!.layoutManager =
                LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        } catch (ex: KotlinNullPointerException) {
            return
        }
        val instance = DoctorHandler.getInstance()
        val doctors = instance.getAll ?: return

        val adapter = DoctorAdapter(doctors) {
            instance.delete(it.id)
        }
        recycler_view.adapter = adapter
    }
}