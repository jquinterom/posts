package com.jhon.posts.interfaces

import androidx.lifecycle.LiveData
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.model.Comment
import com.jhon.posts.model.Post
import com.jhon.posts.model.User

interface PostTasks {
    // API
    suspend fun getPostsCollection(): ApiResponseStatus<List<Post>>
    suspend fun getUsersCollection(): ApiResponseStatus<List<User>>
    suspend fun getPostById(postId: Int): ApiResponseStatus<Post>
    suspend fun getCommentsByPostId(postId: Int): ApiResponseStatus<List<Comment>>
    suspend fun getUserById(userId: Int): ApiResponseStatus<User>

    // Room
    suspend fun getPostByIdDB(postId: Int) : LiveData<Post>
    suspend fun registerPost(post: Post)
}