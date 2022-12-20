package com.jhon.posts.api

import com.jhon.posts.api.dto.*
import com.jhon.posts.constants.*
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

    @GET(GET_USER_BY_ID_URL)
    suspend fun getUserById(@Path("userId") userId: Int): UserDTO

    @GET(GET_ALL_COMMENTS_BY_POST_ID_URL)
    suspend fun getCommentsByPostId(@Path("postId") postId: Int): List<CommentDTO>

    @GET(GET_ALL_ALBUMS_BY_USER_ID_URL)
    suspend fun getAlbumsByUserId(@Path("userId") userId: Int): List<AlbumDTO>

    @GET(GET_ALL_PHOTOS_BY_ALBUM_ID_URL)
    suspend fun getPhotosByAlbumId(@Path("albumId") albumId: Int): List<PhotoDTO>
}