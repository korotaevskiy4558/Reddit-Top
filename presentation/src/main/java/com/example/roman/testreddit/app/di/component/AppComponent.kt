package com.example.roman.testreddit.app.di.component

import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.roman.testreddit.app.AndroidApplication
import com.example.roman.testreddit.app.di.module.ActivityContributorModule
import com.example.roman.testreddit.app.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityContributorModule::class, AppModule::class])
interface ApplicationComponent : AndroidInjector<AndroidApplication> {
    override fun inject(application: AndroidApplication)

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: AndroidApplication): Builder

        fun build(): ApplicationComponent
    }
}