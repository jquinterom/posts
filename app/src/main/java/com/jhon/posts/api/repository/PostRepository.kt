package com.jhon.posts.api.repository

import com.jhon.posts.R
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.api.ApiService
import com.jhon.posts.api.dto.PostDTOMapper
import com.jhon.posts.api.dto.UserDTOMapper
import com.jhon.posts.api.makeNetworkCall
import com.jhon.posts.interfaces.PostTasks
import com.jhon.posts.model.Comment
import com.jhon.posts.model.Post
import com.jhon.posts.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) : PostTasks {

    override suspend fun getPostsCollection(): ApiResponseStatus<List<Post>> {
        return withContext(dispatcher) {
            val allPostsListResponseDeferred = async { downloadPosts() }

            when (val allPostsListResponse = allPostsListResponseDeferred.await()) {
                is ApiResponseStatus.Error -> {
                    allPostsListResponse
                }
                is ApiResponseStatus.Success -> {
                    ApiResponseStatus.Success(
                        getCollectionPostsList(
                            allPostsListResponse.data,
                        )
                    )
                }
                else -> {
                    ApiResponseStatus.Error(R.string.there_was_an_error_posts)
                }
            }
        }
    }

    override suspend fun getUsersCollection(): ApiResponseStatus<List<User>> {
        return withContext(dispatcher) {
            val allUsersListResponseDeferred = async { downloadUsers() }

            when (val allUsersListResponse = allUsersListResponseDeferred.await()) {
                is ApiResponseStatus.Error -> {
                    allUsersListResponse
                }
                is ApiResponseStatus.Success -> {
                    ApiResponseStatus.Success(
                        getCollectionUsersList(
                            allUsersListResponse.data
                        )
                    )
                }
                else -> {
                    ApiResponseStatus.Error(R.string.there_was_an_error_users)
                }
            }
        }
    }

    override suspend fun getPostById(postId: Int): ApiResponseStatus<Post> {
        return withContext(dispatcher) {
            val postResponseDeferred = async { downloadPost(postId) }

            when (val postResponse = postResponseDeferred.await()) {
                is ApiResponseStatus.Error -> {
                    postResponse
                }
                is ApiResponseStatus.Success -> {
                    ApiResponseStatus.Success(
                        getPost(
                            postResponse.data
                        )
                    )
                }
                else -> {
                    ApiResponseStatus.Error(R.string.there_was_an_error_users)
                }
            }
        }
    }


    private fun getPost(post: Post) = post
    private fun getUser(user: User) = user

    override suspend fun getCommentByPostId(): ApiResponseStatus<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(userId: Int): ApiResponseStatus<User> {
        return withContext(dispatcher) {
            val userResponseDeferred = async { downloadUser(userId) }

            when (val userResponse = userResponseDeferred.await()) {
                is ApiResponseStatus.Error -> {
                    userResponse
                }
                is ApiResponseStatus.Success -> {
                    ApiResponseStatus.Success(
                        getUser(
                            userResponse.data
                        )
                    )
                }
                else -> {
                    ApiResponseStatus.Error(R.string.there_was_an_error_users)
                }
            }
        }
    }

    private fun getCollectionPostsList(allPostsList: List<Post>) =
        allPostsList.map { it }

    private fun getCollectionUsersList(allUsersList: List<User>) =
        allUsersList.map { it }

    private suspend fun downloadPosts(): ApiResponseStatus<List<Post>> =
        makeNetworkCall {
            val postListApiResponse = apiService.getAllPosts()
            val postDTOMapper = PostDTOMapper()
            postDTOMapper.fromPostDTOListToPostDomainList(postListApiResponse)
        }

    private suspend fun downloadUsers(): ApiResponseStatus<List<User>> =
        makeNetworkCall {
            val usersListApiResponse = apiService.getAllUsers()
            val userDTOMapper = UserDTOMapper()
            userDTOMapper.fromUserDTOListToUserDomainList(usersListApiResponse)
        }

    private suspend fun downloadPost(postId: Int): ApiResponseStatus<Post> =
        makeNetworkCall {
            val post = apiService.getPostById(postId)
            val postDTOMapper = PostDTOMapper()
            postDTOMapper.formPostDTOToPostDomain(postDTO = post)
        }

    private suspend fun downloadUser(userId: Int): ApiResponseStatus<User> =
        makeNetworkCall {
            val user = apiService.getUserById(userId = userId)
            val userDTOMapper = UserDTOMapper()
            userDTOMapper.formUserDTOToUserDomain(user)
        }


}