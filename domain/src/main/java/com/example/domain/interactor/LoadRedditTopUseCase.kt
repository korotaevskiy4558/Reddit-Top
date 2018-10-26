package com.example.domain.interactor

import com.example.domain.model.Reddit
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.domain.model.RedditList
import com.example.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

class LoadRedditTopUseCase
@Inject
constructor(
        threadExecutor: ThreadExecutor ,
        postExecutionThread: PostExecutionThread,
        private val contentRepository: Repository
) : ObservableUseCase<RedditList, LoadRedditTopUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<RedditList> {
        return contentRepository.loadReddit(params!!.paginationTake,params.after)
    }

    class Params (var after: String, var paginationTake: Int){}

}

