package com.example.roman.testreddit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.roman.testreddit.data_reddit.RepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val repository = SearchRepositoryProvider.provideSearchRepository()
//
//        compositeDisposable.add(
//                repository.searchUsers("Lagos", "Java")
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeOn(Schedulers.io())
//                        .subscribe ({
//                            result ->
//                            Log.e("ResultGit", "There are ${result.items.size} Java developers in Lagos")
//                        }, { error ->
//                            error.printStackTrace()
//                        })
//        )

        val repository = RepositoryProvider.provideRepository()

        compositeDisposable.add(
                repository.searchUsers()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe ({
                            result ->
                            Log.e("ResultGit", "There are ${result.data.children.size} Java developers in Lagos")
                        }, { error ->
                            error.printStackTrace()
                            Log.e("ResultGitError", error.message.toString())
                        })
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
