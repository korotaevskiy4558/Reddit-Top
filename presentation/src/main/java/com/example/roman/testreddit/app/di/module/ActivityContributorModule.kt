package com.example.roman.testreddit.app.di.module

import com.example.roman.testreddit.ui.main.MainActivity
import com.example.roman.testreddit.ui.main.di.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityContributorModule {

//    @ContributesAndroidInjector
//    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}