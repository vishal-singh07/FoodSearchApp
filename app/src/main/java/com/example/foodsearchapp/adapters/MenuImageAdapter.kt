package com.example.foodsearchapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodsearchapp.R
import com.jackandphantom.carouselrecyclerview.view.ReflectionImageView

class MenuImageAdapter(private val images: ArrayList<String>) : RecyclerView.Adapter<MenuImageAdapter.ViewHolder>()  {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
       val iVMenuItemImage: ReflectionImageView = itemView.findViewById(R.id.iVMenuItemImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_corousel_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemImage = images[position]
        if(itemImage==null)
        {
            Glide.with(holder.itemView)
                .load(R.drawable.image)
                .into(holder.iVMenuItemImage)
        }
        else
        {
            Log.i("TAG", "onBindViewHolder: $itemImage")
            Glide.with(holder.itemView)
                .load(itemImage)
                .into(holder.iVMenuItemImage)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}