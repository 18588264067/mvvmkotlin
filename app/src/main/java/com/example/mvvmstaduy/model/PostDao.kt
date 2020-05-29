package com.example.mvvmstaduy.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {

    @get:Query("select * from post")
    val all: List<Post>

    @Insert
    fun insertAll(vararg posts: Post)

}