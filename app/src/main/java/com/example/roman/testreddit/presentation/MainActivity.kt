package com.example.roman.testreddit.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.roman.testreddit.R
import com.example.roman.testreddit.data.entity.Result

import com.example.roman.testreddit.presentation.adapter.RedditAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity(val presenter: MainContract.Presenter = MaintPresenter()) : AppCompatActivity(), MainContract.View,  SwipeRefreshLayout.OnRefreshListener {

    lateinit var redditAdapter: RedditAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var progressBarBottom: ProgressBar
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = this.rv_main
        progressBar = this.pb_main
        progressBarBottom = this.pb_bottom
        swipeRefreshLayout = this.srl_main
        swipeRefreshLayout.setOnRefreshListener(this)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setLayoutManager(layoutManager)
        redditAdapter = RedditAdapter()
        recyclerView.setAdapter(redditAdapter)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount: Int = redditAdapter.itemCount - 1
                val lastVisiblePosition: Int = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                presenter.loadIfNeed(lastVisiblePosition, totalItemCount)
            }
        })
    }

    override fun setItemList(list: MutableList<Result>) {
        redditAdapter.addData(list)

    }

    override fun setNewItem(list: MutableList<Result>) {
        redditAdapter.refreshData(list)
    }


    override fun showProgressBar(show: Boolean) {
        showProgress(progressBar, show)

    }

    override fun showProgressBarBottom(show: Boolean) {
        showProgress(progressBarBottom, show)
    }

    override fun onRefresh() {
        presenter.refreshData()
    }

    override fun showSwipeLoader(show: Boolean) {
       swipeRefreshLayout.setRefreshing(show)
    }

    override fun showMassage(text: String) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
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

    private fun showProgress(progressBar: ProgressBar, show: Boolean) {
        progressBar.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
