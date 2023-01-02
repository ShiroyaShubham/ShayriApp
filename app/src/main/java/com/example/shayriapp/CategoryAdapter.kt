package com.example.shayriapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shayriapp.SharyriDatabase.valueOfQuotes
import java.util.ArrayList

class CategoryAdapter(var context: Context, var list: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtCategory = itemView.findViewById<TextView>(R.id.txtCategory)
        var loutCategoryItem = itemView.findViewById<LinearLayout>(R.id.loutCategoryItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        var myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtCategory.text = list[position].category
        holder.loutCategoryItem.setOnClickListener {
            var intent=Intent(context,ShayariActivity::class.java)
            valueOfQuotes= list[position].id
            intent.putExtra("categoryType",list.get(position).category)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}