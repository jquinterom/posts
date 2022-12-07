package com.jhon.posts.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.api.repository.PostRepository
import com.jhon.posts.constants.FAKE_POST
import com.jhon.posts.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    var post = mutableStateOf<Post>(FAKE_POST)
        private set

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    // name - user
    private val _postId = MutableLiveData<Int>().apply {
        value = 0
    }
    val postId: MutableLiveData<Int> = _postId

    init {
        //getPostDetail()
    }

    fun getPostDetail() {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatusPost(postRepository.getPostById(_postId.value ?: 0))
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusPost(apiResponseStatus: ApiResponseStatus<Post>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            post.value = apiResponseStatus.data
        }

        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    fun setPostId(postId: Int) {
        _postId.value = postId
    }
}






