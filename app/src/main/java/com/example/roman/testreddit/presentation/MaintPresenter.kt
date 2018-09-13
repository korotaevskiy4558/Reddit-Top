package com.example.roman.testreddit.presentation

import android.util.Log
import com.example.roman.testreddit.data.Repository
import com.example.roman.testreddit.data.RepositoryProvider
import com.example.roman.testreddit.data.entity.Child
import com.example.roman.testreddit.data.entity.Data
import com.example.roman.testreddit.data.entity.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MaintPresenter(val compositeDisposable: CompositeDisposable = CompositeDisposable(),
                     val repository: Repository = RepositoryProvider.provideRepository(),
                     var mView: MainContract.View?= null,
                     var after: String = "") : MainContract.Presenter {

    override fun loadIfNeed(lastVisiblePosition: Int, totalItem: Int) {
        if (totalItem - lastVisiblePosition < 5){
            getRedditList();
        }
    }

    override fun getRedditList() {
        mView!!.showProgressBar(false)
        compositeDisposable.add(
                repository.searchUsers(5, after)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ result ->
                            after = result.data.after
                            mView!!.addItemList(convertList(result))
                            mView!!.showProgressBar(true)
                        }
                                , { error ->
                            error.printStackTrace()
                            mView!!.showProgressBar(true)
                            Log.e("ResultGitError", error.message.toString())
                        })
        )
    }

    override fun attachView(view: MainContract.View) {
        mView = view;
    }

    override fun detachView() {
        mView = null
        if (compositeDisposable.isDisposed()){
            compositeDisposable.dispose()
        }
    }

    fun convertList(child: Child): MutableList<Result>{
        val list: MutableList<Result> = mutableListOf()
        for (item in child.data.children) {
            list.add(Result(item.data.id, item.data.title, item.data.author, item.data.subreddit, item.data.created, item.data.thumbnail, item.data.ups, item.data.numComments, item.data.permalink))
        }
        return list;
    }
}