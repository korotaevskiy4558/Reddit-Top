package com.example.roman.testreddit.app.di.module

import android.app.Application
import com.example.data.executor.JobExecutor
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.roman.testreddit.app.AndroidApplication
import com.example.roman.testreddit.app.UIThread
import dagger.Binds
import dagger.Module
@Module
abstract class AppModule {

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @Binds
    abstract fun bindApplication(app: AndroidApplication): Application
}