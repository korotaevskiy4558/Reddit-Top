package com.example.roman.testreddit.data_reddit

import io.reactivex.Observable

class Repository(val apiService: ApiService) {
    fun searchUsers(): Observable<Child> {
        return apiService.search()
    }
}