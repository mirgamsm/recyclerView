package com.example.recyclerview

import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class myRecyclerAdapter(val contacts: ArrayList<Contact>) : RecyclerView.Adapter<myRecyclerAdapter.MyViewHolder>() {

    var count =1
    private val TAG ="MyRecyclerAdapter"
    //inner keyword gives access to outer loop elements
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        //Provides a reference to the views
        val personName =itemView.findViewById<TextView>(R.id.person_name)
        val personAge =itemView.findViewById<TextView>(R.id.person_age)
        val personImage =itemView.findViewById<ImageView>(R.id.person_image)

        init {
            itemView.setOnClickListener{
                val selectedItem =adapterPosition
                Toast.makeText(itemView.context, "You clicked on $selectedItem", Toast.LENGTH_LONG).show()
                Log.d(TAG,"here")
            }

            itemView.setOnLongClickListener{
                val selectedItem = adapterPosition
                contacts.removeAt(selectedItem)
                notifyItemRemoved(selectedItem)
                Toast.makeText(itemView.context, "Removing on $selectedItem", Toast.LENGTH_LONG).show()
                return@setOnLongClickListener true
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //create a new view
        Log.d(TAG, "onCreateHolder: ${count++}")
        val view =LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }
//  replace the contents of a view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem =contacts[position]
        holder.personName.text =currentItem.name
        holder.personAge.text ="Age = ${currentItem.age}"
        //holder.personImage.setImageResource(currentItem.profileImage)

        Picasso.get().load(currentItem.profileImage).into(holder.personImage)

     }

    override fun getItemCount(): Int {
        return contacts.size
    }
}

