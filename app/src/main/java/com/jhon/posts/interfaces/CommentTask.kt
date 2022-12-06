package com.jhon.posts.interfaces

import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.model.Comment

interface CommentTask {
    suspend fun getCommentCollection(): ApiResponseStatus<List<Comment>>
}