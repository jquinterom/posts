package com.jhon.posts.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.interfaces.PostTasks
import com.jhon.posts.model.Post
import com.jhon.posts.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val postRepository: PostTasks,
) : ViewModel() {
    var postList = mutableStateOf<List<Post>>(listOf())
        private set

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    var usersList = mutableStateOf<List<User>>(listOf())
        private set

    init {
        getPostCollection()
        getUserCollection()
    }

    private fun getPostCollection() {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatusPosts(postRepository.getPostsCollection())
        }
    }

    private fun getUserCollection() {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatusUsers(postRepository.getUsersCollection())
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusPosts(
        apiResponseStatusListPosts: ApiResponseStatus<List<Post>>,
    ) {
        if (apiResponseStatusListPosts is ApiResponseStatus.Success) {
            viewModelScope.launch {
                val postsApi = apiResponseStatusListPosts.data.map {
                    val localPost = postRepository.getPostByIdDB(it.id)
                    if (localPost != null) {
                        Post(it.userId, it.id, it.title, it.body, localPost.favorite)
                    } else {
                        Post(it.userId, it.id, it.title, it.body, false)
                    }
                }
                postList.value = postsApi
            }
        }
        status.value = apiResponseStatusListPosts as ApiResponseStatus<Any>
    }


    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusUsers(
        apiResponseStatusListUsers: ApiResponseStatus<List<User>>
    ) {
        if (apiResponseStatusListUsers is ApiResponseStatus.Success) {
            usersList.value = apiResponseStatusListUsers.data
        }
        status.value = apiResponseStatusListUsers as ApiResponseStatus<Any>
    }

    fun resetApiResponseStatus() {
        status.value = null
    }

    fun updatePostDB(post: Post) {
        viewModelScope.launch {
            var postToRegister = Post(post.userId, post.id, post.title, post.body, !post.favorite)
            val localPost = postRepository.getPostByIdDB(post.id)
            if (localPost != null) {
                postToRegister = localPost.copy(
                    userId = localPost.userId,
                    id = localPost.id,
                    title = localPost.title,
                    body = localPost.body,
                    favorite = !localPost.favorite
                )
            }
            postRepository.registerPost(post = postToRegister)
        }
    }
}