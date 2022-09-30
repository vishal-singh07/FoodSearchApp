package com.example.foodsearchapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsearchapp.R
import com.example.foodsearchapp.model.Category

class CategoryAdapter(private val context: Context, private val categories: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tVCategory.text = categories[position].name
        holder.rVMenuItem.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val adapter = MenuAdapter(categories[position].menuItems)
        holder.rVMenuItem.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tVCategory: TextView = itemView.findViewById(R.id.tVCategory)
        val rVMenuItem: RecyclerView = itemView.findViewById(R.id.rVMenuItem)

    }
}