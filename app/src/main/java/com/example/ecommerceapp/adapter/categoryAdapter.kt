package com.example.ecommerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.category
import kotlinx.android.synthetic.main.category_item.view.*

class categoryAdapter(var categoryItems:MutableList<category>):RecyclerView.Adapter<categoryAdapter.myviewHolder>(){
    lateinit var context: Context
   class myviewHolder(v:View):RecyclerView.ViewHolder(v) {
       var categoryImage:ImageView=v.findViewById(R.id.categoryImage)
       var categoryName:TextView=v.findViewById(R.id.categoryName)

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder {
        context=parent.context
        var view=LayoutInflater.from(context).inflate(R.layout.category_item,parent,false)
        return myviewHolder(view)
    }

    override fun onBindViewHolder(holder: myviewHolder, position: Int) {
        holder.categoryImage.setImageResource(categoryItems[position].categoryImage)
        holder.categoryName.setText(categoryItems[position].categoryName)
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }
}