package com.example.ecommerceapp.Fragments

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.categoryAdapter
import com.example.ecommerceapp.data.category
import org.intellij.lang.annotations.JdkConstants


class categoryFragment : Fragment() {

   lateinit var recyclerView:RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var categoryData:MutableList<category>
    lateinit var myadaper:categoryAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_category, container, false)
        recyclerView=view.findViewById(R.id.recyclerView)
        linearLayoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        categoryData= mutableListOf()
        categoryData.add(category(R.drawable.ic_gift,"Gift"))
        categoryData.add(category(R.drawable.ic_gift,"Gift"))
        categoryData.add(category(R.drawable.ic_gift,"Gift"))
        categoryData.add(category(R.drawable.ic_gift,"Gift"))
        categoryData.add(category(R.drawable.ic_gift,"Gift"))
        categoryData.add(category(R.drawable.ic_gift,"Gift"))
        categoryData.add(category(R.drawable.ic_gift,"Gift"))
        categoryData.add(category(R.drawable.ic_gift,"Gift"))
        myadaper=categoryAdapter(categoryData)
        recyclerView.layoutManager=linearLayoutManager
        recyclerView.adapter=myadaper
        return view
    }


}