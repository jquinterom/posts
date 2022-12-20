package com.jhon.posts.interfaces

import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.model.Album
import com.jhon.posts.model.Photo

interface AlbumTask {
    suspend fun getAlbumsCollectionByUserId(userId: Int): ApiResponseStatus<List<Album>>
    suspend fun getPhotosCollectionByAlbumId(albumId: Int) : ApiResponseStatus<List<Photo>>
}