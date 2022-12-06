package com.jhon.posts.api

import com.jhon.posts.api.dto.PostDTO
import com.jhon.posts.constants.GET_ALL_POSTS_URL
import com.jhon.posts.api.responses.PostListApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET(GET_ALL_POSTS_URL)
    //suspend fun getAllPosts(): PostListApiResponse
    suspend fun getAllPosts(): List<PostDTO>
}