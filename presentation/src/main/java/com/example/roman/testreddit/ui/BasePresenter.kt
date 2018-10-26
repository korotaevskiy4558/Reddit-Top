package com.example.roman.testreddit.ui

interface BasePresenter <V> {
    fun attachView(view: V)

    fun detachView()
}