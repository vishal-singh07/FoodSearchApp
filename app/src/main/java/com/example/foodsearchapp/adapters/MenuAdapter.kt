package com.example.foodsearchapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsearchapp.R
import com.example.foodsearchapp.model.Category
import com.example.foodsearchapp.model.MenuItem
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview

class MenuAdapter(private val items: ArrayList<MenuItem>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tVItemName: TextView = itemView.findViewById(R.id.tVItemName)
        val tVItemDescription: TextView = itemView.findViewById(R.id.tVItemDescription)
        val tVItemPrice: TextView = itemView.findViewById(R.id.tVItemPrice)
        val carouselRecyclerView: CarouselRecyclerview = itemView.findViewById(R.id.carouselRecyclerview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tVItemName.text = items[position].name
        holder.tVItemDescription.text = items[position].description
        holder.tVItemPrice.text = items[position].price
        val adapter = MenuImageAdapter(items[position].images)

        holder.carouselRecyclerView.adapter = adapter
        holder.carouselRecyclerView.set3DItem(true)
        holder.carouselRecyclerView.setAlpha(true)
        holder.carouselRecyclerView.setInfinite(true)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}