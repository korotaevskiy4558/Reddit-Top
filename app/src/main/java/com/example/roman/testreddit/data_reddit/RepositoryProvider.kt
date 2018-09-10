package com.example.roman.testreddit.data_reddit



object RepositoryProvider {

    fun provideRepository(): Repository {
        return Repository(ApiService.create())
    }
}