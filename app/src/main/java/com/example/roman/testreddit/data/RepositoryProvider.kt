package com.example.roman.testreddit.data



object RepositoryProvider {
    fun provideRepository(): Repository {
        return Repository(ApiService.create())
    }
}