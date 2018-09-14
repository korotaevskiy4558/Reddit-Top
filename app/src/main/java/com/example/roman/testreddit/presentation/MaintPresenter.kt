package com.example.roman.testreddit.presentation

import com.example.roman.testreddit.Constants
import com.example.roman.testreddit.data.Repository
import com.example.roman.testreddit.data.RepositoryProvider
import com.example.roman.testreddit.data.entity.Child
import com.example.roman.testreddit.data.entity.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MaintPresenter(val compositeDisposable: CompositeDisposable = CompositeDisposable(),
                     val repository: Repository = RepositoryProvider.provideRepository(),
                     var mView: MainContract.View?= null,
                     var after: String = "",
                     var isLoad: Boolean = false) : MainContract.Presenter {

    override fun loadIfNeed(lastVisiblePosition: Int, totalItem: Int) {
        if (totalItem - lastVisiblePosition < 4){
            if (!isLoad) {
                isLoad = true
                getRedditList()
            }

        }
    }

    override fun refreshData() {
        mView!!.showSwipeLoader(true)
        compositeDisposable.add(
                repository.getTop(Constants.PAGINATION_CONSTAT, "")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ result ->
                            after = result.data.after
                            mView!!.setNewItem(convertList(result))
                            mView!!.showSwipeLoader(false)
                            isLoad = false
                        }
                                , { error ->
                            error.printStackTrace()
                            mView!!.showSwipeLoader(false)
                            mView!!.showMassage("error сonnect")
                            isLoad = false
                        })
        )
    }

    override fun getRedditList() {
        mView!!.showProgressBar(true)
        mView!!.showProgressBarBottom(isLoad)
        compositeDisposable.add(
                repository.getTop(Constants.PAGINATION_CONSTAT, after)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ result ->
                            after = result.data.after
                            mView!!.setItemList(convertList(result))
                            mView!!.showProgressBar(false)
                            isLoad = false
                            mView!!.showProgressBarBottom(isLoad)
                        }
                                , { error ->
                            error.printStackTrace()
                            mView!!.showProgressBar(false)
                            mView!!.showMassage("error сonnect")
                            isLoad = false
                        })
        )
    }

    override fun attachView(view: MainContract.View) {
        mView = view
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
            list.add(Result(item.reddit))
        }
        return list
    }
}