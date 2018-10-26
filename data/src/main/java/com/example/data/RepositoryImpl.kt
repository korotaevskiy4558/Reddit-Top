package com.example.data

import com.example.data.entity.Child
import com.example.data.net.ApiService
import com.example.domain.model.Reddit
import com.example.domain.model.RedditList
import com.example.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl
@Inject
constructor() : Repository  {

    private val apiService: ApiService = ApiService.create()

    override fun loadReddit(limit: Int, after: String): Observable<RedditList> {
        return apiService
                .getTop(limit, after)
                .flatMap{child: Child -> transform(child) }


    }

    private fun transform(child: Child): Observable<RedditList> {
        return Observable.fromCallable {
           convertList(child)
        }
    }

    private fun convertList(child: Child): RedditList{
        val list: MutableList<Reddit> = mutableListOf()
        for (item in child.data.children) {
            list.add(Reddit(item.reddit.id, item.reddit.title, item.reddit.author,
                    item.reddit.subreddit, item.reddit.created,item.reddit.thumbnail,
                    item.reddit.ups, item.reddit.numComments, item.reddit.permalink))
        }
        val redditList: RedditList = RedditList(list, child.data.after)
        return redditList
    }

}