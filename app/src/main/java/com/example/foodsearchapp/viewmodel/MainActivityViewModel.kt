package com.example.foodsearchapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodsearchapp.data.Repository
import com.example.foodsearchapp.model.Category
import com.example.foodsearchapp.model.Restaurant

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    fun getRestaurants(): MutableLiveData<ArrayList<Restaurant>> {
        return Repository.getAllRestaurants(context)
    }
    fun getMenuOfRestaurant(id: Int): MutableLiveData<ArrayList<Category>>{
        return Repository.getMenuOfRestaurant(context,id)
    }
    fun getCompleteMenu(): MutableLiveData<HashMap<Int,HashSet<String>>>{
        return Repository.getCompleteMenu(context)
    }

}