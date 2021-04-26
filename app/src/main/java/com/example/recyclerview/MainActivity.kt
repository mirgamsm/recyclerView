package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var refreshCount =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView =findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter =myRecyclerAdapter(generateContact(20))
//        default vertical
        recyclerView.layoutManager =LinearLayoutManager(this)

//        Horizontal
//        recyclerView.layoutManager =LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        val dividerItemDecoration =DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        val swipetoRefresh  =findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        swipetoRefresh.setOnRefreshListener {
            Log.d(TAG, ":Refreshed")

            refreshCount +=2
            recyclerView.adapter =myRecyclerAdapter(generateContact(20+refreshCount))
            swipetoRefresh.isRefreshing =false


        }

    }
    fun generateContact(size: Int): ArrayList<Contact> {
        val contacts =ArrayList<Contact>()

        for(i in 1..size){
            val person =Contact("John Doe->$i",i,"https://picsum.photos/150?random=$i")
            contacts.add(person)
        }
        contacts.reverse()

        return contacts
    }
}