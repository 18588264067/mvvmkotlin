package com.example.mvvmstaduy.ui.post

import com.example.mvvmstaduy.base.BaseViewModel
import com.example.mvvmstaduy.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel: BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { onRetrievePostListSuccess() },
                { onRetrievePostListError() })
    }

    private fun onRetrievePostListError() {
        TODO("Not yet implemented")
    }

    private fun onRetrievePostListSuccess() {
        TODO("Not yet implemented")
    }

    private fun onRetrievePostListFinish() {
        TODO("Not yet implemented")
    }

    private fun onRetrievePostListStart() {
        TODO("Not yet implemented")
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}