package com.example.domain.executor

import io.reactivex.Scheduler

/**
 * Created by korot on 24.10.2018.
 */
interface PostExecutionThread {
    val scheduler: Scheduler

}