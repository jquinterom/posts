package com.jhon.posts.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhon.posts.model.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM Post WHERE id = :postId")
    suspend fun getPostById(postId: Int) : Post

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post)
}