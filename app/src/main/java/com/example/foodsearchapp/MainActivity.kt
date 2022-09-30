package com.example.foodsearchapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsearchapp.adapters.RestaurantAdapter
import com.example.foodsearchapp.model.Category
import com.example.foodsearchapp.model.Restaurant
import com.example.foodsearchapp.viewmodel.MainActivityViewModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var restaurantRecyclerView: RecyclerView
    private lateinit var listener: ItemClickListener
    private lateinit var restaurants: ArrayList<Restaurant>
    private lateinit var menuMap: HashMap<Int, HashSet<String>>
    private lateinit var adapter: RestaurantAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        restaurantRecyclerView = findViewById<RecyclerView>(R.id.rvRestaurants)
        getRestaurants()
        listener = object : ItemClickListener {
            override fun onClick(id: Int) {
                val intent = Intent(this@MainActivity, CategoryActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
            }
        }
        getMenu()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.search_menu,menu)
        val item = menu?.findItem(R.id.searchAction)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText!!)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
    private fun filter(text: String) {
        val filteredList = ArrayList<Restaurant>()
        val restaurantIdsContainingSearchedItem = HashSet<Int>()
        for(key in menuMap.keys)
        {
            if(menuMap[key]?.contains(text.lowercase(Locale.getDefault())) == true)
                restaurantIdsContainingSearchedItem.add(key)
        }
        for (item in restaurants) {
            if ((item.name!!.lowercase().contains(text.lowercase(Locale.getDefault())))||
                (item.cuisineType!!.lowercase().contains(text.lowercase(Locale.getDefault())))||
                (restaurantIdsContainingSearchedItem.contains(item.id))) {
                filteredList.add(item)
            }
        }
        if (filteredList.isNotEmpty()) {
            adapter.filterList(filteredList)
        }
    }

    private fun getRestaurants() {
        mainActivityViewModel.getRestaurants().observe(this, Observer {
            restaurants = it
            restaurantRecyclerView.layoutManager = LinearLayoutManager(this)
            adapter = RestaurantAdapter(listener,it)
            restaurantRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()

        })


    }
    private fun getMenu(){
            mainActivityViewModel.getCompleteMenu().observe(this, Observer {
                menuMap = it
            })
    }

}