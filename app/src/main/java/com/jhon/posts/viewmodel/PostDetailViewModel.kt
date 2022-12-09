package com.jhon.posts.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.api.repository.PostRepository
import com.jhon.posts.constants.FAKE_POST
import com.jhon.posts.constants.FAKE_USER
import com.jhon.posts.model.Post
import com.jhon.posts.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    var post = mutableStateOf(FAKE_POST)
        private set

    var user = mutableStateOf(FAKE_USER)
        private set

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    fun getPostDetail(postId: Int, userId: Int) {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatusPost(postRepository.getPostById(postId))
            handleResponseStatusUser(postRepository.getUserById(userId = userId))
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusPost(apiResponseStatus: ApiResponseStatus<Post>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            post.value = apiResponseStatus.data
        }

        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }


    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusUser(apiResponseStatus: ApiResponseStatus<User>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            user.value = apiResponseStatus.data
        }

        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    fun resetApiResponseStatus() {
        status.value = null
    }
}






