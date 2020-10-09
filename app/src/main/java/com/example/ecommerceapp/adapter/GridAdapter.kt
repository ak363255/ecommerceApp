package com.example.ecommerceapp.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.GridProductModelClass

class GridAdapter (var data:MutableList<GridProductModelClass>):RecyclerView.Adapter<GridAdapter.myViewHolder>() {
    lateinit var context:Context
    class myViewHolder(view:View):RecyclerView.ViewHolder(view){
            var image:ImageView=view.findViewById(R.id.imageView)
            var productName:TextView=view.findViewById(R.id.productName)
            var features:TextView=view.findViewById(R.id.features)
           var price:TextView=view.findViewById(R.id.price)
        var parent:ConstraintLayout=view.findViewById(R.id.parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        context=parent.context
        var view=LayoutInflater.from(context).inflate(R.layout.product_item,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        var bitmap = BitmapFactory.decodeResource(context.resources, data[position].image)
        var dominantColor: Int = ContextCompat.getColor(context, R.color.colorAccent)
        Palette.Builder(bitmap).generate {
            it?.let { palette ->
                dominantColor =
                    palette.getDominantColor(ContextCompat.getColor(context, R.color.colorAccent))
                holder.image.setBackgroundColor(dominantColor)
                holder.parent.background.setTint(dominantColor)

            }
           // holder.parent.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryDark))
            holder.image.setImageResource(data[position].image)
            holder.productName.setText(data[position].productName)
            holder.features.setText(data[position].feature+"- 855 SnapDragon")
            holder.price.setText("Rs -"+data[position].price+"/")
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}