package com.example.mvvmstaduy.ui.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.mvvmstaduy.R
import com.example.mvvmstaduy.base.BaseViewModel
import com.example.mvvmstaduy.model.Post
import com.example.mvvmstaduy.model.PostDao
import com.example.mvvmstaduy.network.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao): BaseViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    val postListAdapter: PostListAdapter = PostListAdapter()

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription =  Observable.fromCallable{ postDao.all }
            .concatMap {
                dbPostList ->
                if (dbPostList.isEmpty()) postApi.getPosts().concatMap {
                    apiPostList -> postDao.insertAll(*apiPostList.toTypedArray())
                    Observable.just(apiPostList)
                }
                else
                    Observable.just(dbPostList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() })
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrievePostListSuccess(result: List<Post>) {
        postListAdapter.updatePostList(result)
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null;
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}