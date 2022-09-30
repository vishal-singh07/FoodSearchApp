package com.example.foodsearchapp.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.foodsearchapp.model.*
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException

object Repository {
    private var restaurants: MutableLiveData<ArrayList<Restaurant>> = MutableLiveData<ArrayList<Restaurant>>()
    private var menu: MutableLiveData<ArrayList<Category>> = MutableLiveData<ArrayList<Category>>()
    private var menuMapPerRestaurant: MutableLiveData<HashMap<Int,HashSet<String>>> = MutableLiveData()
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
    fun getAllRestaurants(context: Context): MutableLiveData<ArrayList<Restaurant>>{
        val jsonFileString = getJsonDataFromAsset(context, "Restaurants.json")
        val jsonObject = JSONTokener(jsonFileString).nextValue() as JSONObject
        val jsonArray = jsonObject.getJSONArray("restaurants")
        val restaurantsList = ArrayList<Restaurant>()
        for (i in 0 until jsonArray.length()){
            val id = jsonArray.getJSONObject(i).getInt("id")
            val name = jsonArray.getJSONObject(i).getString("name")
            val neighborhood = jsonArray.getJSONObject(i).getString("neighborhood")
            val photograph = jsonArray.getJSONObject(i).getString("photograph")
            val address = jsonArray.getJSONObject(i).getString("address")
            val cuisineType = jsonArray.getJSONObject(i).getString("cuisine_type")
            val locationObj = jsonArray.getJSONObject(i).getJSONObject("latlng")
            val latitude = locationObj.getDouble("lat")
            val longitude = locationObj.getDouble("lng")
            val workingHoursObj = jsonArray.getJSONObject(i).getJSONObject("operating_hours")
            val mondayHours = workingHoursObj.getString("Monday")
            val tuesdayHours = workingHoursObj.getString("Tuesday")
            val wednesdayHours = workingHoursObj.getString("Wednesday")
            val thursdayHours = workingHoursObj.getString("Thursday")
            val fridayHours = workingHoursObj.getString("Friday")
            val saturdayHours = workingHoursObj.getString("Saturday")
            val sundayHours = workingHoursObj.getString("Sunday")
            val reviews = jsonArray.getJSONObject(i).getJSONArray("reviews")
            val reviewList = ArrayList<Review>()
            for(j in 0 until reviews.length()){
                val reviewerName = reviews.getJSONObject(j).getString("name")
                val reviewDate = reviews.getJSONObject(j).getString("date")
                val ratings = reviews.getJSONObject(j).getInt("rating")
                val comments = reviews.getJSONObject(j).getString("comments")
                val review = Review(reviewerName,reviewDate,ratings,comments)
                reviewList.add(review)
            }
            val restaurant = Restaurant(
                id,
                name,
                neighborhood,
                photograph,
                address,
                GeographicLocation(latitude,longitude),
                cuisineType,
                OperatingHours(mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours, saturdayHours, sundayHours),
                reviewList
            )
            restaurantsList.add(restaurant)
        }
        restaurants.postValue(restaurantsList)
        return restaurants
    }
    fun getMenuOfRestaurant(context: Context, id: Int) : MutableLiveData<ArrayList<Category>>{
        val jsonFileString = getJsonDataFromAsset(context, "Menus.json")
        val jsonObject = JSONTokener(jsonFileString).nextValue() as JSONObject
        val jsonArray = jsonObject.getJSONArray("menus")
        val categoryList = ArrayList<Category>()
        for (i in 0 until jsonArray.length()){
            if(jsonArray.getJSONObject(i).getInt("restaurantId")==id)
            {
                val categoryArray = jsonArray.getJSONObject(i).getJSONArray("categories")
                for(j in 0 until categoryArray.length())
                {
                    val id = categoryArray.getJSONObject(j).getString("id")
                    val name = categoryArray.getJSONObject(j).getString("name")
                    val menuItemsList = ArrayList<MenuItem>()
                    val menuItemArray = categoryArray.getJSONObject(j).getJSONArray("menu-items")
                    for(item in 0 until menuItemArray.length())
                    {
                        val itemId = menuItemArray.getJSONObject(item).getString("id")
                        val itemName = menuItemArray.getJSONObject(item).getString("name")
                        val itemDescription = menuItemArray.getJSONObject(item).getString("description")
                        val itemPrice = menuItemArray.getJSONObject(item).getString("price")
                        val itemImages = ArrayList<String>()
                        val itemImageArray = menuItemArray.getJSONObject(item).getJSONArray("images")
                        for(image in 0 until itemImageArray.length())
                            itemImages.add(itemImageArray.getString(image))
                        menuItemsList.add(
                            MenuItem(
                            itemId,
                            itemName,
                            itemDescription,
                            itemPrice,
                            itemImages
                        )
                        )
                    }
                    categoryList.add(
                        Category(
                        id,
                        name,
                        menuItemsList
                    )
                    )
                }
            }
        }
        menu.postValue(categoryList)
        return menu;
    }

    fun getCompleteMenu(context: Context) : MutableLiveData<HashMap<Int,HashSet<String>>>{
        val jsonFileString = getJsonDataFromAsset(context, "Menus.json")
        val jsonObject = JSONTokener(jsonFileString).nextValue() as JSONObject
        val jsonArray = jsonObject.getJSONArray("menus")
        val menuMap = HashMap<Int,HashSet<String>>()
        for (i in 0 until jsonArray.length()){
                val restaurantId = jsonArray.getJSONObject(i).getInt("restaurantId")
                val categoryArray = jsonArray.getJSONObject(i).getJSONArray("categories")
                for(j in 0 until categoryArray.length())
                {
                    val menuItemsSet = HashSet<String>()
                    val menuItemArray = categoryArray.getJSONObject(j).getJSONArray("menu-items")
                    for(item in 0 until menuItemArray.length())
                    {
                        val itemName = menuItemArray.getJSONObject(item).getString("name")
                        menuItemsSet.add(itemName.lowercase())
                    }
                    menuMap[restaurantId] = menuItemsSet
                }
            }

        menuMapPerRestaurant.postValue(menuMap)
        return menuMapPerRestaurant;
    }

}