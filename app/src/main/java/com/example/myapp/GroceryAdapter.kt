package com.example.myapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import org.w3c.dom.Text


class GroceryAdapter(val data: List<GroceryItem>): RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {

    inner class GroceryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.rv_image)
        val item = itemView.findViewById<TextView>(R.id.rv_item)
        val qty = itemView.findViewById<TextView>(R.id.rv_qty)
        val price = itemView.findViewById<TextView>(R.id.rv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_single, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        Log.d(TAG,"data : ${data[position]}")
        holder.item.text = data[position].title
        holder.price.text = data[position].original_price
        holder.qty.text = data[position].quantity
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
        Glide.with(holder.itemView).load(data[position].image_url).apply(options).into(
            holder.image
        )
    }

    override fun getItemCount() = data.size
    companion object{
        private const val TAG = "GroceryAdapter"
    }
}