package com.example.data

import com.example.data.entity.Child
import io.reactivex.Observable

interface ApiService {

    @retrofit2.http.GET("top.json")
    fun getTop(
            @retrofit2.http.Query("limit", encoded = false) limit: Int,
            @retrofit2.http.Query("after", encoded = false) after: String ): Observable<Child>

    companion object Factory {
        const val URL = "https://www.reddit.com/"
        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .baseUrl(URL)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}