package step.ahead.group.sugar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import io.realm.RealmList
import step.ahead.group.sugar.R
import step.ahead.group.sugar.models.TestResult


class ReportAdapter(
    private val results: RealmList<TestResult>,
    val onClick: (result: TestResult) -> Unit
) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, isActive: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_report, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val result = results[position]

        /*holder.name.text = result?.result_name
        holder.price.text = result?.price.toString()

        holder.addButton.setOnClickListener { onClick(result!!) }*/

    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        /*var name: TextView = item.findViewById(R.id.mtnbaqaname)
        var price:TextView = item.findViewById(R.id.mtnbaraprice)
        var addButton: Button = item.findViewById(R.id.MtnAddBaqah)*/
    }


}