package com.example.roman.testreddit.ui.main

import com.example.domain.interactor.EmptyObserver
import com.example.domain.interactor.LoadRedditTopUseCase
import com.example.domain.model.RedditList
//import com.example.roman.testreddit.data.Repository
//import com.example.roman.testreddit.data.RepositoryProvider
import javax.inject.Inject

class MaintPresenter
@Inject
constructor(val loadRedditTopUseCase: LoadRedditTopUseCase) : MainContract.Presenter {

    private var mView: MainContract.View? = null
    private var after: String = ""
    private var isLoad: Boolean = false

    override fun attachView(view: MainContract.View) {
        mView = view
    }

    override fun detachView() {
        mView = null
        loadRedditTopUseCase.dispose()
    }

    override fun loadIfNeed(lastVisiblePosition: Int, totalItem: Int) {
        if (totalItem - lastVisiblePosition < 4){
            if (!isLoad) {
                isLoad = true
                getRedditList()
            }

        }
    }

    override fun getRedditList() {
        mView!!.showProgressBar(true)
        mView!!.showProgressBarBottom(isLoad)
        loadRedditTopUseCase.execute(observer = LoadObserver(), params = LoadRedditTopUseCase.Params(after, 10))
    }

    private inner class LoadObserver : EmptyObserver<RedditList>() {
        override fun onNext(t: RedditList) {
            super.onNext(t)
            after = t.after
            mView!!.setItemList(t.redditList)
            isLoad = false
            mView!!.showProgressBar(isLoad)
            mView!!.showProgressBarBottom(isLoad)
        }

    }

    override fun refreshData(){
        after = ""
        isLoad = true
        mView!!.showSwipeLoader(isLoad)
        loadRedditTopUseCase.execute(observer = RefreshObserver(), params = LoadRedditTopUseCase.Params(after, 10))
    }

    private inner class RefreshObserver : EmptyObserver<RedditList>() {
        override fun onNext(t: RedditList) {
            super.onNext(t)
            after = t.after
            mView!!.setNewItem(t.redditList)
            isLoad = false
            mView!!.showSwipeLoader(isLoad)
        }

    }
}