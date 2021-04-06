package step.ahead.group.sugar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.my_drags_fragment.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.adapters.DrugAdapter
import step.ahead.group.sugar.dialogs.AddDrugDialog
import step.ahead.group.sugar.handlers.DrugHandler


class DrugFragment : MasterStuffFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.my_drags_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_btn.setOnClickListener {
            AddDrugDialog().show(requireActivity().supportFragmentManager, "drug")
        }
    }
    override fun onResume() {
        super.onResume()
        //show()
        // اين الريسايكلر في هذا الفراجمنت؟؟؟
    }
    private fun show() {
        try {
            recycler_view!!.layoutManager =
                LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        } catch (ex: KotlinNullPointerException) {
            return
        }
        val instance = DrugHandler.getInstance()
        val drugs = instance.getAll ?: return

        val adapter = DrugAdapter(drugs) {
            instance.delete(it.id)
        }
        recycler_view.adapter = adapter
    }
}
