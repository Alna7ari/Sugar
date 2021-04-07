package step.ahead.group.sugar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import step.ahead.group.sugar.R
import step.ahead.group.sugar.models.Drug
import step.ahead.group.sugar.utils.DateUtil


class DrugAdapter(private val doctors: RealmResults<Drug>, val onClick: (doctor: Drug) -> Unit) : RecyclerView.Adapter<DrugAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, isActive: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_notifactions, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return doctors.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val doctor = doctors[position]

        holder.name.text = doctor?.name
        holder.time.text = DateUtil.getDate(doctor?.useTime)
        holder.removeButton.setOnClickListener {
            try {
                onClick(doctor!!)
                //results.remove(result)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,doctors.size)
            } catch (e: Exception){}
        }

    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var name: TextView = item.findViewById(R.id.name)
        var time:TextView = item.findViewById(R.id.time)
        var removeButton: ImageView = item.findViewById(R.id.remove_btn)
    }


}