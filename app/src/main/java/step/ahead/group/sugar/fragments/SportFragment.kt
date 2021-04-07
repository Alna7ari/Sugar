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
import step.ahead.group.sugar.adapters.SportAdapter
import step.ahead.group.sugar.dialogs.AddDrugDialog
import step.ahead.group.sugar.handlers.SportHandler
import step.ahead.group.sugar.utils.FragmentUtils


class SportFragment : MasterStuffFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.my_sports_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_btn.setOnClickListener {
            AddDrugDialog().show(requireActivity().supportFragmentManager, "drug")
        }
        close_btn.setOnClickListener {
            FragmentUtils().open(activity, MoreFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        //show()
    }
    private fun show() {
        try {
            recycler_view!!.layoutManager =
                LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        } catch (ex: KotlinNullPointerException) {
            return
        }
        val instance = SportHandler.getInstance()
        val sports = instance.getAll ?: return

        val adapter = SportAdapter(sports) {
            instance.delete(it.id)
        }
        recycler_view.adapter = adapter
    }
}