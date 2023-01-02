package com.example.shayriapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class DisplayShayariAdapter(var quotesList: ArrayList<QuotesModel>) : RecyclerView.Adapter<DisplayShayariAdapter.MyViewHolder>() {
    var color= arrayOf(R.color.pink,R.color.cream,R.color.yellow,R.color.purple)
    var i=0
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    var txtShayari=itemView.findViewById<TextView>(R.id.txtShayari)
        var loutBackground=itemView.findViewById<LinearLayout>(R.id.loutBackground)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.shayari_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtShayari.text = quotesList[position].quotes
        holder.loutBackground.setBackgroundResource(color[i])
        i++
        if(i==4){
            i=0
        }
    }

    override fun getItemCount(): Int {
        return quotesList.size
    }
}