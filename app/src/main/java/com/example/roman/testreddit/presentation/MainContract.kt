package com.example.roman.testreddit.presentation

import com.example.roman.testreddit.data.entity.Data
import com.example.roman.testreddit.data.entity.Result

interface MainContract {

    interface View {
        fun addItemList(list: MutableList<Result>)
        fun showProgressBar(show: Boolean)
    }

    interface Presenter : BasePresenter<View>{
        fun loadIfNeed(lastVisiblePosition: Int, totalItem: Int)
        fun getRedditList()
    }

    interface Item{
        fun openItem(url: String)
    }

}