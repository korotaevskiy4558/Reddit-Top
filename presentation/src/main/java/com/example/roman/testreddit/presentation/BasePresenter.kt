package com.example.roman.testreddit.presentation

interface BasePresenter <V> {
    fun attachView(view: V)

    fun detachView()
}