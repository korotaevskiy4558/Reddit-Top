package com.example.roman.testreddit.presentation

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.MonthDisplayHelper
import android.view.View
import android.widget.ProgressBar
import com.example.roman.testreddit.R
import com.example.roman.testreddit.data.entity.Result

import com.example.roman.testreddit.presentation.adapter.RedditAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity(val presenter: MainContract.Presenter = MaintPresenter()) : AppCompatActivity(), MainContract.View {

    lateinit var redditAdapter: RedditAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = this.rv_main
        progressBar = this.pb_main
        displayRedditList()

    }




    private fun displayRedditList() {
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setLayoutManager(layoutManager)
        redditAdapter = RedditAdapter()
        recyclerView.setAdapter(redditAdapter)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var totalItemCount: Int = redditAdapter.itemCount - 1
                var lastVisiblePosition: Int = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                presenter.loadIfNeed(lastVisiblePosition, totalItemCount)
            }
        })
    }

    override fun addItemList(list: MutableList<Result>) {
        redditAdapter.addData(list)
    }

    override fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        presenter!!.attachView(this)
        presenter.getRedditList()
    }


    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    val obj = object: MainContract.Item{
        override fun openItem(url: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

}
