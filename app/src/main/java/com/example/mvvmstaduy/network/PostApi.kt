package com.example.mvvmstaduy.network

import com.example.mvvmstaduy.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface PostApi {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}