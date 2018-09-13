package com.example.roman.testreddit.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.roman.testreddit.R
import com.example.roman.testreddit.data.entity.Result
import com.example.roman.testreddit.presentation.MainContract
import kotlinx.android.synthetic.main.activity_main.view.*

class RedditAdapter() : RecyclerView.Adapter<RedditHolder>() {
    var items:  MutableList<Result> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_reddit,parent,false)
        val viewHolder = RedditHolder(view, parent.context)
        return viewHolder;
    }

    override fun getItemCount(): Int {
      return items.size
    }

    override fun onBindViewHolder(holder: RedditHolder, position: Int) {
        holder.bindView(items.get(position))
    }

    fun addData(listReddit: MutableList<Result>){
        var curSize: Int = itemCount
        items.addAll(listReddit)
        notifyItemRangeInserted(curSize, items.size - 1)

    }
}