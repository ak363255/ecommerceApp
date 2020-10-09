package com.example.ecommerceapp.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.BannerDataModel

class bannerAdapter(var mdata:MutableList<BannerDataModel>,var layout:ConstraintLayout):RecyclerView.Adapter<bannerAdapter.myviewHolder>() {
   lateinit var context:Context
    class myviewHolder(v:View):RecyclerView.ViewHolder(v){
        var image:ImageView=v.findViewById(R.id.bannerImage)
        var parent:ConstraintLayout=v.findViewById(R.id.bannerparent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder
    {
        context=parent.context
        var view=LayoutInflater.from(parent.context).inflate(R.layout.banner_item,parent,false)
       return myviewHolder(view)
    }



    override fun getItemCount(): Int {
        return mdata.size
    }

    override fun onBindViewHolder(holder: myviewHolder, position: Int) {
        var bitmap=BitmapFactory.decodeResource(context.resources,mdata[position].image)
        var dominantColor:Int=ContextCompat.getColor(context,R.color.colorAccent)
        Palette.Builder(bitmap).generate{it?.let{palette ->
            dominantColor=palette.getDominantColor(ContextCompat.getColor(context,R.color.colorAccent))
            holder.parent.setBackgroundColor(dominantColor)
            layout.background.setTint(dominantColor)


        }
        }
        var pl=Palette.from(bitmap).generate()
        var vibrant=pl.vibrantSwatch
        Log.d("color",""+vibrant?.rgb)
        if(vibrant!=null){
            //holder.parent.setBackgroundColor(vibrant.rgb)
        }
        holder.image.setImageResource(mdata[position].image)
    }
}