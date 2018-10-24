package com.example.data

import com.example.data.entity.Child
import com.example.domain.model.Reddit
import com.example.domain.repository.Repository
import io.reactivex.Observable
import io.reactivex.internal.operators.single.SingleInternalHelper.toObservable



class RepositoryImpl(val apiService: ApiService) : Repository  {
    override fun loadReddit(limit: Int, after: String): Observable<MutableList<Reddit>> {
        return apiService
                .getTop(limit, after)
                .flatMap{child: Child -> transform(child) }


    }



    private fun transform(child: Child): Observable<MutableList<Reddit>> {
        return Observable.fromCallable {
           convertList(child)
        }
    }

    private fun convertList(child: Child): MutableList<Reddit>{
        val list: MutableList<Reddit> = mutableListOf()
        for (item in child.data.children) {
            list.add(Reddit(item.reddit.id, item.reddit.title, item.reddit.author,
                    item.reddit.subreddit, item.reddit.created,item.reddit.thumbnail,
                    item.reddit.ups, item.reddit.numComments, item.reddit.permalink))
        }
        return list
    }

}