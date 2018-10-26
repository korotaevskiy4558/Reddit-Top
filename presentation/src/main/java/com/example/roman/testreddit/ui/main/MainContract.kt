package com.example.roman.testreddit.ui.main

import com.example.domain.model.Reddit
import com.example.roman.testreddit.ui.BasePresenter

interface MainContract {

    interface View {
        fun setItemList(list: MutableList<Reddit>)
        fun setNewItem(list: MutableList<Reddit>)
        fun showSwipeLoader(show: Boolean)
        fun showProgressBar(show: Boolean)
        fun showProgressBarBottom(show: Boolean)
        fun showMassage(text: String)
    }

    interface Presenter : BasePresenter<View> {
        fun loadIfNeed(lastVisiblePosition: Int, totalItem: Int)
        fun getRedditList()
        fun refreshData()
    }
}