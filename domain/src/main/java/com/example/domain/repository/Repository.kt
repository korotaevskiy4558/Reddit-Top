package com.example.domain.repository

import com.example.domain.model.Reddit
import io.reactivex.Observable

interface Repository {

    fun loadReddit(limit: Int, after: String): Observable<MutableList<Reddit>>
}