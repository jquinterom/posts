package com.jhon.posts.repository

import com.jhon.posts.api.ApiService
import com.jhon.posts.api.dto.*
import com.jhon.posts.constants.FAKE_POST
import java.net.UnknownHostException

class FakeServices {
    companion object {
        val fakePost: PostDTO = PostDTO(
            1, 2, "Post 2", "Body for fake post 2"
        )
    }

    class FakeApiService : ApiService {
        override suspend fun getAllPosts(): List<PostDTO> {
            return listOf(
                PostDTO(1, 1, FAKE_POST.title, FAKE_POST.body),
                fakePost
            )
        }

        override suspend fun getAllUsers(): List<UserDTO> {
            TODO("Not yet implemented")
        }

        override suspend fun getPostById(postId: Int): PostDTO {
            TODO("Not yet implemented")
        }

        override suspend fun getUserById(userId: Int): UserDTO {
            TODO("Not yet implemented")
        }

        override suspend fun getCommentsByPostId(postId: Int): List<CommentDTO> {
            TODO("Not yet implemented")
        }

        override suspend fun getAlbumsByUserId(userId: Int): List<AlbumDTO> {
            TODO("Not yet implemented")
        }

        override suspend fun getPhotosByAlbumId(albumId: Int): List<PhotoDTO> {
            TODO("Not yet implemented")
        }

    }

    class FakeApiServiceToError: ApiService {
        override suspend fun getAllPosts(): List<PostDTO> {
            throw UnknownHostException()
        }

        override suspend fun getAllUsers(): List<UserDTO> {
            throw UnknownHostException()
        }

        override suspend fun getPostById(postId: Int): PostDTO {
            TODO("Not yet implemented")
        }

        override suspend fun getUserById(userId: Int): UserDTO {
            TODO("Not yet implemented")
        }

        override suspend fun getCommentsByPostId(postId: Int): List<CommentDTO> {
            TODO("Not yet implemented")
        }

        override suspend fun getAlbumsByUserId(userId: Int): List<AlbumDTO> {
            TODO("Not yet implemented")
        }

        override suspend fun getPhotosByAlbumId(albumId: Int): List<PhotoDTO> {
            TODO("Not yet implemented")
        }

    }
}