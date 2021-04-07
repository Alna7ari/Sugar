package step.ahead.group.sugar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import step.ahead.group.sugar.R
import step.ahead.group.sugar.models.TestResult
import step.ahead.group.sugar.utils.DateUtil


class ReportAdapter(
    private val results: RealmResults<TestResult>,
    val onClick: (result: TestResult) -> Unit
) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, isActive: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.cardview_report,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val result = results[position]

        //holder.state = result.result
        val testResult = result?.result ?: 0.0
        holder.result.text = testResult.toString()
        holder.time.text = DateUtil.getDate(result?.createdAt)
        holder.state.text = if (testResult < 60) "منخفض جدا" else if (testResult < 80) "منخفض" else if (testResult < 120) "جيدة" else "مرتفعة"
        holder.removeButton.setOnClickListener {
            holder.cardLayout.visibility = View.GONE

            try {
                onClick(result!!)
                //results.remove(result)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,results.size)
            } catch (e: Exception){}
        }
        /*holder.name.text = result?.result_name
        holder.price.text = result?.price.toString()

        holder.addButton.setOnClickListener { onClick(result!!) }*/

    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val state: TextView = item.findViewById(R.id.status_report)
        val result: TextView = item.findViewById(R.id.results)
        val time: TextView = item.findViewById(R.id.time_report)
        val removeButton: ImageView = item.findViewById(R.id.remove)
        val cardLayout: ConstraintLayout = item.findViewById(R.id.card_view)
    }
}