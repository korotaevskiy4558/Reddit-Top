package com.example.roman.testreddit.presentation

import com.example.roman.testreddit.data.entity.Result

interface MainContract {

    interface View {
        fun setItemList(list: MutableList<Result>)
        fun setNewItem(list: MutableList<Result>)
        fun showSwipeLoader(show: Boolean)
        fun showProgressBar(show: Boolean)
        fun showProgressBarBottom(show: Boolean)
        fun showMassage(text: String)
    }

    interface Presenter : BasePresenter<View>{
        fun loadIfNeed(lastVisiblePosition: Int, totalItem: Int)
        fun getRedditList()
        fun refreshData()
    }
}