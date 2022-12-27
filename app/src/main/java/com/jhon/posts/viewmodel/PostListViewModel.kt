package com.jhon.posts.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.constants.FAKE_POST
import com.jhon.posts.interfaces.PostTasks
import com.jhon.posts.model.Post
import com.jhon.posts.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
            postList.value = apiResponseStatusListPosts.data
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

    fun updatePostFavorite(post: Post){
        // TODO: register or update favorite post
    }
}