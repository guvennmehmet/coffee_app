package com.example.coffee_app.feature.coffeelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffee_app.R
import com.example.coffee_app.model.Coffee


class CoffeeAdapter (private val coffeeList:List<Coffee>, val listener: CoffeeClickListener)
    : RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var coffeeCard: CardView
        var coffeeNameText: TextView
        var coffeeDescriptionText: TextView
        var coffeeImage: ImageView
        var favouriteButton: ImageButton

        init {
            coffeeCard = view.findViewById(R.id.coffeeCard)
            coffeeNameText = view.findViewById(R.id.coffeeNameText)
            coffeeDescriptionText = view.findViewById(R.id.coffeeDescriptionText)
            coffeeImage = view.findViewById(R.id.coffeeImage)
            favouriteButton = view.findViewById(R.id.favouriteButton)

            coffeeCard.setOnClickListener {
                listener.coffeeOnClick(coffeeList[bindingAdapterPosition])
            }

            favouriteButton.setOnClickListener {
                if (coffeeList[bindingAdapterPosition].isFavorite) {
                    favouriteButton.setImageDrawable(ContextCompat.getDrawable(view.context,R.drawable.ic_baseline_star_outline_24))
                } else {
                    favouriteButton.setImageDrawable(ContextCompat.getDrawable(view.context,R.drawable.ic_baseline_star_24))
                }
                listener.changeFavorite(coffeeList[bindingAdapterPosition])
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_coffee, parent, false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coffee = coffeeList[position]

        holder.coffeeNameText.text = coffee.name
        holder.coffeeDescriptionText.text = coffee.description

        Glide.with(holder.itemView.context)
            .load(coffeeList[position].image)
            .into(holder.coffeeImage)

        if (coffeeList[position].isFavorite) {
            holder.favouriteButton.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context,R.drawable.ic_baseline_star_24))
        } else {
            holder.favouriteButton.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context,R.drawable.ic_baseline_star_outline_24))
        }
    }

    override fun getItemCount(): Int {
        return  coffeeList.size
    }

}

interface CoffeeClickListener {
    fun coffeeOnClick(coffee: Coffee)
    fun changeFavorite(coffee: Coffee)
}












