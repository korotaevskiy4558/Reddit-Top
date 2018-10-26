package com.example.roman.testreddit.app

import com.example.roman.testreddit.app.di.component.DaggerApplicationComponent
import dagger.android.support.DaggerApplication

class AndroidApplication : DaggerApplication() {
    private val applicationInjector = DaggerApplicationComponent.builder()
            .application(this)
            .build()

    override fun applicationInjector() = applicationInjector


}