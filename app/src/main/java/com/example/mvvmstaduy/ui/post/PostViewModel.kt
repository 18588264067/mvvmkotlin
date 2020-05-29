package com.example.mvvmstaduy.ui.post

import androidx.lifecycle.MutableLiveData
import com.example.mvvmstaduy.base.BaseViewModel
import com.example.mvvmstaduy.model.Post

class PostViewModel: BaseViewModel(){
    private val postTitle:MutableLiveData<String> = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post) {
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostBody(): MutableLiveData<String> {
        return postBody
    }
}