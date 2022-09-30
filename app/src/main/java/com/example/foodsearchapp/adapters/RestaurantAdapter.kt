package com.example.foodsearchapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodsearchapp.ItemClickListener
import com.example.foodsearchapp.R
import com.example.foodsearchapp.model.Restaurant

class RestaurantAdapter(private val listener: ItemClickListener, private var restaurants: ArrayList<Restaurant>) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurantImageUri = restaurants[position].photograph
        if(restaurantImageUri==null)
        {
            Glide.with(holder.itemView)
                .load(R.drawable.image)
                .into(holder.iVRestaurant)
        }
        else
        {
            Glide.with(holder.itemView)
                .load(restaurantImageUri)
                .into(holder.iVRestaurant)
        }
        holder.tVRestaurantName.text = restaurants[position].name
        holder.tVRestaurantAddress.text = restaurants[position].address
        holder.container.setOnClickListener(View.OnClickListener {
            listener.onClick(restaurants[position].id!!)
        })

    }

    override fun getItemCount(): Int {
       return restaurants.size
    }
    fun filterList(filteredList: ArrayList<Restaurant>) {
        restaurants = filteredList
        notifyDataSetChanged()
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val iVRestaurant: ImageView = itemView.findViewById(R.id.iVRestaurant)
        val tVRestaurantName: TextView = itemView.findViewById(R.id.tVRestaurantName)
        val tVRestaurantAddress: TextView = itemView.findViewById(R.id.tVRestaurantAddress)
        val container: CardView = itemView.findViewById(R.id.container)
    }

}