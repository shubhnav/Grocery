package com.example.myapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.rv)
        var dataString = ""
        try {
             dataString = parseJson(this)
        }
        catch (e:Throwable){
            Log.d(TAG,"onCreate ${e.message}")
        }
        val convertedObject: JsonObject = Gson().fromJson(dataString, JsonObject::class.java)
        Log.d(TAG,"onCreate ${convertedObject}")
        val items = convertedObject.get("items").asJsonObject
        val products = items.get("product_list").asJsonArray
        Log.d(TAG,"onCreate ${products}")

        //Log.d(TAG,"onCreate ${products}")
        val groceryList = mutableListOf<GroceryItem>()
        for (i in 0 until products.size()) {
            val jsonobject: JsonObject = products.get(i).asJsonObject
            val id = jsonobject.get("id").asString
            val url = jsonobject.get("image_url").asString
            val title = jsonobject.get("title").asString
            val qty = jsonobject.get("quantity").asString
            val original_price = jsonobject.get("original_price").asString
            //Log.d(TAG,"onCreate ${url}")
            groceryList.add(GroceryItem(id,url,title,qty,original_price))
        }
        Log.d(TAG,"onCreate $groceryList")
        //val test = listOf(GroceryItem("id","image","iyem","5","22"))
        rv.adapter = GroceryAdapter(groceryList)
        rv.layoutManager = LinearLayoutManager(this)

    }


    private fun parseJson(context: Context): String {
        return context.resources.openRawResource(R.raw.product).bufferedReader().use{it.readText()}
    }
    companion object{
        private const val TAG = "MainActivity"
    }
}

