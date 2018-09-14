package com.example.roman.testreddit.presentation.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.roman.testreddit.Constants
import com.example.roman.testreddit.R
import com.example.roman.testreddit.data.entity.Result
import kotlinx.android.synthetic.main.item_reddit.view.*

class RedditHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

    val time: Long = System.currentTimeMillis() / 1000

    fun bindView(result: Result) {
        this.itemView.tv_item_rating.setText(sizeCount(result.ups))
        this.itemView.tv_item_subreddit.setText(result.subreddit)
        this.itemView.tv_item_name.setText(result.author)
        this.itemView.tv_item_title.setText(result.title)
        this.itemView.tv_item_num_comment.setText(sizeCount(result.numComments)+ " "+ context.getString(R.string.comments))
        this.itemView.tv_item_date.setText(getTime(result))
        Glide.with(itemView)
                .load(result.thumbnail)
                .into(this.itemView.iv_item)
        this.itemView.setOnClickListener {
            openItem(result.permalink)
        }
    }

    fun sizeCount(count: Int): String {
        val length: Int = count.toString().length
        if (length < 4) return count.toString()
        else return ((count / 1000).toString() + "K")
    }

    fun getTime(result: Result): String {
        val postTime: Long = Math.round((time - result.created).toDouble() / 60 / 60 ) + 8
        return ((postTime).toString() + " hour ago")
    }

    fun openItem(link: String) {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(context.getColor(R.color.colorPrimary))
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.back))
        builder.build().launchUrl(context, Uri.parse(Constants.URL + link))
    }


}