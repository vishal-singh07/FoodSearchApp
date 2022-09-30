package com.example.foodsearchapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsearchapp.adapters.CategoryAdapter
import com.example.foodsearchapp.viewmodel.MainActivityViewModel

class CategoryActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var categoryRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val intent = intent
        val restaurantId = intent.getIntExtra("id",0)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        categoryRecyclerView = findViewById<RecyclerView>(R.id.rVCategory)
        getCategories(restaurantId)

    }

    private fun getCategories(id: Int) {
        mainActivityViewModel.getMenuOfRestaurant(id).observe(this, Observer {
            val categories = it
            categoryRecyclerView.layoutManager = LinearLayoutManager(this)
            val adapter = CategoryAdapter(this,categories)
            categoryRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }
}