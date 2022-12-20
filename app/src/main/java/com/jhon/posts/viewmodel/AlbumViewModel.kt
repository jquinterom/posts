package com.jhon.posts.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.interfaces.AlbumTask
import com.jhon.posts.model.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val albumRepository: AlbumTask,
) : ViewModel() {
    var albumsList = mutableStateOf<List<Album>>(listOf())
        private set

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set


    fun getAlbumsByUserId(userId: Int){
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatusAlbums(albumRepository.getAlbumsCollectionByUserId(userId = userId))
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusAlbums(apiResponseAlbumsCollectionByUserId: ApiResponseStatus<List<Album>>) {
        if(apiResponseAlbumsCollectionByUserId is ApiResponseStatus.Success){
            albumsList.value = apiResponseAlbumsCollectionByUserId.data
        }

        status.value = apiResponseAlbumsCollectionByUserId as ApiResponseStatus<Any>
    }

    fun resetApiResponseStatus() {
        status.value = null
    }
}