package step.ahead.group.sugar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.adapters.DoctorAdapter
import step.ahead.group.sugar.adapters.FoodAdapter
import step.ahead.group.sugar.handlers.FoodHandler


class FoodFragment : MasterStuffFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
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
        val instance = FoodHandler.getInstance()
        val foods = instance.getAll ?: return

        val adapter = FoodAdapter(foods) {
            instance.delete(it.id)
        }
        recycler_view.adapter = adapter
    }
}