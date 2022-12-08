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

    private val _postId = MutableLiveData<Int>().apply {
        value = 0
    }
    val postId: MutableLiveData<Int> = _postId

    private val _userId = MutableLiveData<Int>().apply {
        value = 0
    }
    val userId: MutableLiveData<Int> = _userId


    fun getPostDetail() {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatusPost(postRepository.getPostById(_postId.value ?: 0))
            handleResponseStatusUser(postRepository.getUserById(_userId.value ?: 0))
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


    fun setPostId(postId: Int) {
        _postId.value = postId
    }

    fun setUserId(userId: Int) {
        _userId.value = userId
    }

    fun resetApiResponseStatus() {
        status.value = null
    }
}






