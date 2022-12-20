package com.jhon.posts.api.repository

import com.jhon.posts.R
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.api.ApiService
import com.jhon.posts.api.dto.AlbumDTOMapper
import com.jhon.posts.api.dto.PhotoDTOMapper
import com.jhon.posts.api.makeNetworkCall
import com.jhon.posts.interfaces.AlbumTask
import com.jhon.posts.model.Album
import com.jhon.posts.model.Photo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) : AlbumTask {

    override suspend fun getAlbumsCollectionByUserId(userId: Int): ApiResponseStatus<List<Album>> {
        return withContext(dispatcher) {
            val allAlbumsListByUserIdResponseDeferred = async { downloadAlbumsByUserId(userId) }

            when (val allAlbumsResponse = allAlbumsListByUserIdResponseDeferred.await()) {
                is ApiResponseStatus.Error -> {
                    allAlbumsResponse
                }
                is ApiResponseStatus.Success -> {
                    ApiResponseStatus.Success(
                        getCollectionAlbumsList(
                            allAlbumsResponse.data,
                        )
                    )
                }
                else -> {
                    ApiResponseStatus.Error(R.string.there_was_an_error_posts)
                }
            }
        }
    }

    private suspend fun downloadAlbumsByUserId(userId: Int): ApiResponseStatus<List<Album>> =
        makeNetworkCall {
            val albumsListApiResponse = apiService.getAlbumsByUserId(userId)
            val albumDTOMapper = AlbumDTOMapper()
            albumDTOMapper.fromAlbumDTOListToAlbumDomainList(albumsListApiResponse)
        }


    private fun getCollectionAlbumsList(albumsList: List<Album>) = albumsList.map { it }

    override suspend fun getPhotosCollectionByAlbumId(albumId: Int): ApiResponseStatus<List<Photo>> {
        return withContext(dispatcher) {
            val allPhotosListByAlbumIdResponseDeferred = async { downloadPhotosByUserId(albumId) }

            when (val allPhotosResponse = allPhotosListByAlbumIdResponseDeferred.await()) {
                is ApiResponseStatus.Error -> {
                    allPhotosResponse
                }
                is ApiResponseStatus.Success -> {
                    ApiResponseStatus.Success(
                        getCollectionPhotosList(
                            allPhotosResponse.data,
                        )
                    )
                }
                else -> {
                    ApiResponseStatus.Error(R.string.there_was_an_error_posts)
                }
            }
        }
    }

    private suspend fun downloadPhotosByUserId(albumId: Int): ApiResponseStatus<List<Photo>> =
        makeNetworkCall {
            val photosListApiResponse = apiService.getPhotosByAlbumId(albumId)
            val photoDTOMapper = PhotoDTOMapper()
            photoDTOMapper.fromPhotoDTOListToPhotoDomainList(photosListApiResponse)
        }


    private fun getCollectionPhotosList(photosList: List<Photo>) = photosList.map { it }
}