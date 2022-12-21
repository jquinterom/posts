package com.jhon.posts.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.interfaces.AlbumTask
import com.jhon.posts.model.Album
import com.jhon.posts.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val albumRepository: AlbumTask,
) : ViewModel() {
    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    var albumsList = mutableStateOf<List<Album>>(listOf())
        private set

    var photosList = mutableStateOf<List<Photo>>(listOf())
        private set


    fun getAlbumsByUserId(userId: Int){
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatusAlbums(albumRepository.getAlbumsCollectionByUserId(userId = userId))
        }
    }

    fun getPhotosByAlbumId(albumId: Int){
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatusPhotos(albumRepository.getPhotosCollectionByAlbumId(albumId = albumId))
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusPhotos(photosCollectionByAlbumId: ApiResponseStatus<List<Photo>>) {
        if(photosCollectionByAlbumId is ApiResponseStatus.Success){
            photosList.value = photosCollectionByAlbumId.data
        }
        status.value = photosCollectionByAlbumId as ApiResponseStatus<Any>
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