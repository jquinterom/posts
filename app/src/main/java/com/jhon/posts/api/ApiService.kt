package com.jhon.posts.api

import com.jhon.posts.api.dto.PostDTO
import com.jhon.posts.api.dto.UserDTO
import com.jhon.posts.constants.GET_ALL_POSTS_URL
import com.jhon.posts.constants.GET_ALL_USERS_URL
import com.jhon.posts.constants.GET_POST_BY_ID_URL
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(GET_ALL_POSTS_URL)
    //suspend fun getAllPosts(): PostListApiResponse
    suspend fun getAllPosts(): List<PostDTO>

    @GET(GET_ALL_USERS_URL)
    suspend fun getAllUsers(): List<UserDTO>

    @GET(GET_POST_BY_ID_URL)
    suspend fun getPostById(@Path("postId") postId: Int): PostDTO
}