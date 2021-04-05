package step.ahead.group.sugar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.more_fragment.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.dialogs.AddDoctorDialog
import step.ahead.group.sugar.dialogs.AddDrugDialog
import step.ahead.group.sugar.utils.FragmentUtils


class MoreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.more_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // هنانستمع لزر اضفة الدواء ونفتح الديالوج عند الضغط
        add_drags_btn.setOnClickListener {
            AddDrugDialog().show(requireActivity().supportFragmentManager, "drug")
        }
        // هنا نستمع لزر المزيد ونفتح فراجمينت الادوية
        // لاحظ انة تم تمرير فراجمينت الادوية ك باراميتر للدالة open
        more_drug_btn.setOnClickListener {
            FragmentUtils().open(activity, DrugFragment())
            // test
        }
        // طبعا بتنسخ الاثنين الي فوق وتكررهم لكل الموجودين في صقحة المزيد مثل طبيبي ورياضتي وغيرها
        // امسح التعليقات هولا بعدما تكون فهمت ونفذت المهمة بعدها ارفعن
        // هنانستمع لزر اضفة الدواء ونفتح الديالوج عند الضغط
        add_doctor_btn.setOnClickListener {
            AddDoctorDialog().show(requireActivity().supportFragmentManager, "drug")
        }
        // هنا نستمع لزر المزيد ونفتح فراجمينت الادوية
        // لاحظ انة تم تمرير فراجمينت الادوية ك باراميتر للدالة open
        more_doctor_btn.setOnClickListener {
            FragmentUtils().open(activity, DoctorFragment())
        }

        super.onViewCreated(view, savedInstanceState)
    }
}