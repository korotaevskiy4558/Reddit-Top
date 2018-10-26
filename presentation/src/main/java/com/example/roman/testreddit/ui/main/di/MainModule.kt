package com.example.roman.testreddit.ui.main.di

import com.example.data.RepositoryImpl
import com.example.domain.repository.Repository
import com.example.roman.testreddit.ui.main.MainContract
import com.example.roman.testreddit.ui.main.MaintPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {
    @Binds
    abstract fun bindPresenter(presenter: MaintPresenter): MainContract.Presenter

    @Binds
    abstract fun bindContentRepository(repositoryImpl: RepositoryImpl): Repository
}