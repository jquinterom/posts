package com.jhon.posts.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.api.repository.PostRepository
import com.jhon.posts.constants.FAKE_POST
import com.jhon.posts.constants.FAKE_USER
import com.jhon.posts.model.Comment
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

    var listComments = mutableStateOf<List<Comment>>(emptyList())
        private set

    private var comments = mutableStateOf<List<Comment>>(emptyList())

    private var statusComments = mutableStateOf(false)

    var showedComment = mutableStateOf(false)
        private set

    var statusLoadComments = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    fun getPostDetail(postId: Int) {
        viewModelScope.launch {
            handleResponseStatusPost(postRepository.getPostById(postId))
            handleResponseStatusUser(postRepository.getUserById(userId = post.value.userId))
        }
    }

    fun getComments(postId: Int) {
        viewModelScope.launch {
            if (!statusComments.value) {
                if (listComments.value.isEmpty()) {
                    statusLoadComments.value = ApiResponseStatus.Loading()
                    handleResponseStatusComments(postRepository.getCommentsByPostId(postId))
                }
            } else {
                listComments.value = emptyList()
                statusComments.value = !statusComments.value
            }
        }

        showedComment.value = !showedComment.value
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusComments(apiResponseStatus: ApiResponseStatus<List<Comment>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            listComments.value = apiResponseStatus.data
            statusComments.value = true
            comments.value = listComments.value
        } else {
            statusComments.value = false
            comments.value = emptyList()
        }

        statusLoadComments.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusPost(apiResponseStatus: ApiResponseStatus<Post>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            post.value = apiResponseStatus.data
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusUser(apiResponseStatus: ApiResponseStatus<User>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            user.value = apiResponseStatus.data
        }
    }

    fun resetApiResponseStatus() {
        statusLoadComments.value = null
    }

}






