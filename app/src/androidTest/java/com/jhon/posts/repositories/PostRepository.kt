package com.jhon.posts.repositories

import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.constants.FAKE_POST
import com.jhon.posts.interfaces.PostTasks
import com.jhon.posts.model.Comment
import com.jhon.posts.model.Post
import com.jhon.posts.model.User
import com.jhon.posts.R
import com.jhon.posts.constants.FAKE_USER

class PostRepository {

    class FakePostRepository : PostTasks {
        override suspend fun getPostsCollection(): ApiResponseStatus<List<Post>> {
            return ApiResponseStatus.Loading()
        }

        override suspend fun getUsersCollection(): ApiResponseStatus<List<User>> {
            return ApiResponseStatus.Loading()
        }

        override suspend fun getPostById(postId: Int): ApiResponseStatus<Post> {
            TODO("Not yet implemented")
        }

        override suspend fun getCommentsByPostId(postId: Int): ApiResponseStatus<List<Comment>> {
            TODO("Not yet implemented")
        }

        override suspend fun getUserById(userId: Int): ApiResponseStatus<User> {
            TODO("Not yet implemented")
        }

        override suspend fun getPostByIdDB(postId: Int): Post {
            TODO("Not yet implemented")
        }

        override suspend fun registerPost(post: Post) {
            TODO("Not yet implemented")
        }

    }

    class FakePostRepositoryError : PostTasks {
        override suspend fun getPostsCollection(): ApiResponseStatus<List<Post>> {
            return ApiResponseStatus.Error(messageId = R.string.unknown_error)
        }

        override suspend fun getUsersCollection(): ApiResponseStatus<List<User>> {
            return ApiResponseStatus.Error(messageId = R.string.unknown_error)
        }

        override suspend fun getPostById(postId: Int): ApiResponseStatus<Post> {
            TODO("Not yet implemented")
        }

        override suspend fun getCommentsByPostId(postId: Int): ApiResponseStatus<List<Comment>> {
            TODO("Not yet implemented")
        }

        override suspend fun getUserById(userId: Int): ApiResponseStatus<User> {
            TODO("Not yet implemented")
        }

        override suspend fun getPostByIdDB(postId: Int): Post {
            TODO("Not yet implemented")
        }

        override suspend fun registerPost(post: Post) {
            TODO("Not yet implemented")
        }
    }


    class FakePostRepositorySuccess : PostTasks {
        override suspend fun getPostsCollection(): ApiResponseStatus<List<Post>> {
            return ApiResponseStatus.Success(
                listOf(
                    FAKE_POST,
                    Post(1, 1, "Post 2", "Body for fake post 2")
                )
            )
        }

        override suspend fun getUsersCollection(): ApiResponseStatus<List<User>> {
            return ApiResponseStatus.Success(
                listOf(
                    FAKE_USER,
                    User(1, "Fake User 2", "", "", "", "")
                )
            )
        }

        override suspend fun getPostById(postId: Int): ApiResponseStatus<Post> {
            TODO("Not yet implemented")
        }

        override suspend fun getCommentsByPostId(postId: Int): ApiResponseStatus<List<Comment>> {
            TODO("Not yet implemented")
        }

        override suspend fun getUserById(userId: Int): ApiResponseStatus<User> {
            TODO("Not yet implemented")
        }

        override suspend fun getPostByIdDB(postId: Int): Post {
            return FAKE_POST
        }

        override suspend fun registerPost(post: Post) {
            TODO("Not yet implemented")
        }
    }

}