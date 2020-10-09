package com.example.ecommerceapp.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.BannerDataModel
import com.example.ecommerceapp.data.GridProductModelClass
import com.example.ecommerceapp.data.HomeDataModel
import com.example.ecommerceapp.data.StripAdvDataModel
import com.google.api.Distribution
import javax.annotation.meta.When

class HomeAdapter(var mdata:MutableList<HomeDataModel>) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
          lateinit var context:Context

        class StripAddViewHolder(  view:View):RecyclerView.ViewHolder(view){
          var image:ImageView=view.findViewById(R.id.stripAddImage)
            var layout:ConstraintLayout=view.findViewById(R.id.parent)
       }
        class gridProductViewHolder(view:View):RecyclerView.ViewHolder(view)
        {
                 var gridLayout:RecyclerView=view.findViewById(R.id.gridView)
        }
        class bannerViewHolder(view:View):RecyclerView.ViewHolder(view){
             var viewPager:ViewPager2=view.findViewById(R.id.bannerViewPager)
            var layout:ConstraintLayout=view.findViewById(R.id.frameLayout)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context=parent.context

        lateinit var v:View
        when(viewType){
            0->{
                    v=LayoutInflater.from(context).inflate(R.layout.product_grid_layout,parent,false)
                   return gridProductViewHolder(v)
            }
            1->{
                v=LayoutInflater.from(context).inflate(R.layout.strip_add_item,parent,false)
                return StripAddViewHolder(v)
            }
            2->{
                v=LayoutInflater.from(context).inflate(R.layout.fragment_banner,parent,false)
                return bannerViewHolder(v)
            }
        }
        return StripAddViewHolder(v)

    }



    override fun getItemCount(): Int {
        return mdata.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            0->{
                setGridLayout(mdata[position].gridlayoutProduct,holder as gridProductViewHolder)
            }
            1->{
                setStripAddData(mdata[position].stripAdvData,holder as StripAddViewHolder)
            }
            2->{
                setBannerData(mdata[position].bannerData,holder as bannerViewHolder)
            }
        }


    }

    private fun setGridLayout(gridlayoutProduct: MutableList<GridProductModelClass>, gridProductViewHolder: HomeAdapter.gridProductViewHolder) {
        var gridLayoutManager=GridLayoutManager(context,2)
        gridProductViewHolder.gridLayout.layoutManager=gridLayoutManager
        gridProductViewHolder.gridLayout.addItemDecoration(SpacesItemDecoration(20))
        gridProductViewHolder.gridLayout.adapter=GridAdapter(gridlayoutProduct)
    }

    private fun setBannerData(data: MutableList<BannerDataModel>, bannerViewHolder: HomeAdapter.bannerViewHolder) {
        var handler: Handler = Handler()
        var viewPager = bannerViewHolder.viewPager
        viewPager.adapter=bannerAdapter(data,bannerViewHolder.layout)
        var runnable: Runnable = object : Runnable {
            override fun run() {
                if (viewPager.currentItem == data.size - 1) {
                    viewPager.setCurrentItem(0, false)
                } else {
                    viewPager.setCurrentItem(viewPager.currentItem + 1, true)
                }
            }


        }
        viewPager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
               // viewPager.get(position).setBackgroundColor(dominantColor)
                handler.postDelayed(runnable,3000)
            }
        })

        viewPager.get(viewPager.currentItem).setOnTouchListener(object:View.OnTouchListener{
            override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
                Log.d("down","true")
                when(event?.action){
                    MotionEvent.ACTION_DOWN->{
                        handler.removeCallbacks(runnable)
                    }
                    MotionEvent.ACTION_UP->{
                        handler.postDelayed(runnable,3000)
                    }

                }
                return false
            }

        })
    }


    private fun setStripAddData(stripAdvData: StripAdvDataModel, stripAddViewHolder: HomeAdapter.StripAddViewHolder) {
        var bitmap = BitmapFactory.decodeResource(context.resources, stripAdvData.image)
        var dominantColor: Int = ContextCompat.getColor(context, R.color.colorAccent)
        Palette.Builder(bitmap).generate {
            it?.let { palette ->
                dominantColor =
                    palette.getDominantColor(ContextCompat.getColor(context, R.color.colorAccent))
                stripAddViewHolder.layout.background.setTint(dominantColor)

            }
            stripAddViewHolder.image.setImageResource(stripAdvData.image)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return mdata[position].type
    }

     class SpacesItemDecoration : RecyclerView.ItemDecoration {
        private var space:Int;
        constructor( space:Int) {
            this.space = space;
        }

         override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State)
         {
             super.getItemOffsets(outRect, view, parent, state)
             outRect.left = space;
             outRect.right = space;
             outRect.bottom = space;
             // Add top margin only for the first item to avoid double space between items

         }
    }
}