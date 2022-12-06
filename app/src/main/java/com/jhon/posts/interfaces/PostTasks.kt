package com.jhon.posts.interfaces

import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.model.Post

interface PostTasks {
    suspend fun getPostsCollection(): ApiResponseStatus<List<Post>>
}