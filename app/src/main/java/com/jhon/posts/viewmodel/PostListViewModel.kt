package com.jhon.posts.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.interfaces.PostTasks
import com.jhon.posts.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val postRepository: PostTasks
): ViewModel(){
    var postList = mutableStateOf<List<Post>>(listOf())
    private set

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
    private set

    init {
        getPostCollection()
    }

    private fun getPostCollection() {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatus(postRepository.getPostsCollection())
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<Post>>) {
        if(apiResponseStatus is ApiResponseStatus.Success){
            postList.value = apiResponseStatus.data
        }

        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    fun resetApiResponseStatus(){
        status.value = null
    }
}