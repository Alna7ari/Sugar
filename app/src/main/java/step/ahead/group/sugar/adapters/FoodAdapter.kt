package step.ahead.group.sugar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import step.ahead.group.sugar.R
import step.ahead.group.sugar.models.Food


class FoodAdapter(
    private val foods: RealmResults<Food>,
    val onClick: (food: Food) -> Unit
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, isActive: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_report, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foods.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val food = foods[position]

        /*holder.name.text = food?.food_name
        holder.price.text = food?.price.toString()

        holder.addButton.setOnClickListener { onClick(food!!) }*/

    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        /*var name: TextView = item.findViewById(R.id.mtnbaqaname)
        var price:TextView = item.findViewById(R.id.mtnbaraprice)
        var addButton: Button = item.findViewById(R.id.MtnAddBaqah)*/
    }


}