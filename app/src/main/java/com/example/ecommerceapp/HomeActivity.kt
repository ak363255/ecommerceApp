package com.example.ecommerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.Fragments.categoryFragment
import com.example.ecommerceapp.adapter.HomeAdapter
import com.example.ecommerceapp.data.BannerDataModel
import com.example.ecommerceapp.data.GridProductModelClass
import com.example.ecommerceapp.data.HomeDataModel
import com.example.ecommerceapp.data.StripAdvDataModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawer:DrawerLayout
    lateinit var container:FrameLayout
    lateinit var recyclerView: RecyclerView
    lateinit var linearLayoutManager:LinearLayoutManager
    lateinit var myAdapter:HomeAdapter
    lateinit var Homedata:MutableList<HomeDataModel>
    lateinit var data:MutableList<BannerDataModel>
    lateinit var stripadvData:MutableList<StripAdvDataModel>
    lateinit var productData:MutableList<GridProductModelClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar=findViewById(R.id.tool_bar)
        drawer=findViewById(R.id.drawer)
        container=findViewById(R.id.container)
        setSupportActionBar(tool_bar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)
        recyclerView=findViewById(R.id.recyclerView)
        navview.setNavigationItemSelectedListener ( object: NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if(item.itemId==R.id.signout)
                {
                    FirebaseAuth.getInstance().signOut()
                    finishAffinity()
                }
                return true
            }

        } )
        stripadvData= mutableListOf()
        data= mutableListOf()
       productData= mutableListOf()
        stripadvData.add(StripAdvDataModel(R.drawable.candy))
        Homedata= mutableListOf()
        ///////
        data.add(BannerDataModel(R.drawable.book1))
        data.add(BannerDataModel(R.drawable.book2))
        data.add(BannerDataModel(R.drawable.book3))
        data.add(BannerDataModel(R.drawable.book4))
        data.add(BannerDataModel((R.drawable.book5)))
        data.add(BannerDataModel((R.drawable.book5)))
        data.add(BannerDataModel((R.drawable.book6)))
        /////////////////////////////////
        ///////////////////
        productData.add(GridProductModelClass(R.drawable.book11,"Redmi","processor","4999"))
        productData.add(GridProductModelClass(R.drawable.book22,"Redmi","processor","4999"))
        productData.add(GridProductModelClass(R.drawable.book33,"Redmi","processor","4999"))
        productData.add(GridProductModelClass(R.drawable.book44,"Redmi","processor","4999"))
        productData.add(GridProductModelClass(R.drawable.book55,"Redmi","processor","4999"))
        productData.add(GridProductModelClass(R.drawable.book66,"Redmi","processor","4999"))
        productData.add(GridProductModelClass(R.drawable.book77,"Redmi","processor","4999"))

        ///////////////////////////////

        Homedata.add(HomeDataModel(data as MutableList<Any>,2))
        Homedata.add(HomeDataModel(StripAdvDataModel(R.drawable.shopping),1))
        Homedata.add(HomeDataModel(productData as MutableList<Any>,0))
        myAdapter= HomeAdapter(Homedata)
        linearLayoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=linearLayoutManager
        recyclerView.adapter=myAdapter
        setCategory()
    }


    private fun setCategory() {
        supportFragmentManager.beginTransaction().replace(container.id,categoryFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbarmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            drawer.openDrawer(Gravity.LEFT);

        }
        return true
    }
}