package com.example.roman.testreddit.data_reddit

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {
    //    @GET("search/users")
//    fun search(@Query("q") query: String,
//               @Query("page") page: Int = 1,
//               @Query("per_page") perPage: Int = 20): Observable<Result>
    @GET("top.json")
    fun search(@Query("limit", encoded = false) limit: Int = 5): Observable<Child>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
//                    .client(
//                            OkHttpClient.Builder().connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
//                            .readTimeout(60 * 1000, TimeUnit.MILLISECONDS).build()
//                    )
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()
//                            GsonBuilder().setLenient().create())
                    )
                    .baseUrl("https://www.reddit.com/")
                    .build()

            return retrofit.create(ApiService::class.java);
        }
    }
}