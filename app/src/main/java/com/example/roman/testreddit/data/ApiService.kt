package com.example.roman.testreddit.data

import com.example.roman.testreddit.Constants
import com.example.roman.testreddit.data.entity.Child
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top.json")
    fun search(
            @Query("limit", encoded = false) limit: Int ,
            @Query("after", encoded = false) after: String ): Observable<Child>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.URL)
                    .build()

            return retrofit.create(ApiService::class.java);
        }
    }
}