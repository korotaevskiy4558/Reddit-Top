package com.example.roman.testreddit.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.model.Reddit
import com.example.roman.testreddit.R

class RedditAdapter() : RecyclerView.Adapter<RedditHolder>() {
    var items:  MutableList<Reddit> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_reddit,parent,false)
        val viewHolder = RedditHolder(view, parent.context)
        return viewHolder
    }

    override fun getItemCount(): Int {
      return items.size
    }

    override fun onBindViewHolder(holder: RedditHolder, position: Int) {
        holder.bindView(items.get(position))
    }

    fun addData(listReddit: MutableList<Reddit>){
        val curSize: Int = itemCount
        items.addAll(listReddit)
        notifyItemRangeInserted(curSize, items.size - 1)

    }

    fun refreshData(listReddit: MutableList<Reddit>){
        items.clear()
        items.addAll(listReddit)
        notifyDataSetChanged()
    }
}