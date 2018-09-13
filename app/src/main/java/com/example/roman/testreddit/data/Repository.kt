package com.example.roman.testreddit.data

import com.example.roman.testreddit.data.entity.Child
import io.reactivex.Observable

class Repository(val apiService: ApiService) {
    fun searchUsers(limit: Int, after: String): Observable<Child> {
        return apiService.search(limit, after)
    }
}