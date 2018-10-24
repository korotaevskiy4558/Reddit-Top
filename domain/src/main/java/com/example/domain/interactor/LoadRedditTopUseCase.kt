package com.example.domain.interactor

import com.example.domain.model.Reddit
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.domain.repository.Repository
import io.reactivex.Observable

class LoadRedditTopUseCase
constructor(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        private val contentRepository: Repository
) : ObservableUseCase<MutableList<Reddit>, Unit>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit?): Observable<MutableList<Reddit>> {
        return contentRepository.loadReddit(10,"")
    }

}

