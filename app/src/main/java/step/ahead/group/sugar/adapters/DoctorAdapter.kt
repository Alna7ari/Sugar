package step.ahead.group.sugar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import io.realm.RealmList
import io.realm.RealmResults
import step.ahead.group.sugar.R
import step.ahead.group.sugar.models.Doctor


class DoctorAdapter(
    private val doctors: RealmResults<Doctor>,
    val onClick: (doctor: Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, isActive: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_report, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return doctors.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val doctor = doctors[position]

        /*holder.name.text = doctor?.doctor_name
        holder.price.text = doctor?.price.toString()

        holder.addButton.setOnClickListener { onClick(doctor!!) }*/

    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        /*var name: TextView = item.findViewById(R.id.mtnbaqaname)
        var price:TextView = item.findViewById(R.id.mtnbaraprice)
        var addButton: Button = item.findViewById(R.id.MtnAddBaqah)*/
    }


}