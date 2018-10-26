package com.example.domain.repository

import com.example.domain.model.RedditList
import io.reactivex.Observable

interface Repository {

    fun loadReddit(limit: Int, after: String): Observable<RedditList>
}