package com.jhon.posts.api.repository

import com.jhon.posts.R
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.api.ApiService
import com.jhon.posts.api.dto.PostDTOMapper
import com.jhon.posts.api.makeNetworkCall
import com.jhon.posts.interfaces.PostTasks
import com.jhon.posts.model.Post
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

            val allPostsListResponse = allPostsListResponseDeferred.await()

            when {
                allPostsListResponse is ApiResponseStatus.Error -> {
                    allPostsListResponse
                }

                allPostsListResponse is ApiResponseStatus.Success -> {
                    ApiResponseStatus.Success(
                        getCollectionList(
                            allPostsListResponse.data,
                        )
                    )
                }
                else -> {
                    ApiResponseStatus.Error(R.string.there_was_an_error)
                }
            }
        }
    }

    private fun getCollectionList(allPostsList: List<Post>) =
        allPostsList.map { it }

    private suspend fun downloadPosts(): ApiResponseStatus<List<Post>> =
        makeNetworkCall {
            val postListApiResponse = apiService.getAllPosts()
            val postDTOMapper = PostDTOMapper()
            postDTOMapper.fromPostDTOListToPostDomainList(postListApiResponse)
        }

}