package com.jhon.posts.api.dto

import com.jhon.posts.model.Photo

class PhotoDTOMapper {
    private fun fromPhotoDTOToPhotoDomain(photoDTO: PhotoDTO): Photo {
        return Photo(
            albumId = photoDTO.albumId,
            id = photoDTO.id,
            title = photoDTO.title,
            url = photoDTO.url,
            thumbnailUrl = photoDTO.thumbnailUrl,
        )
    }

    fun fromPhotoDTOListToPhotoDomainList(photoDTOList: List<PhotoDTO>): List<Photo> {
        return photoDTOList.map {
            fromPhotoDTOToPhotoDomain(it)
        }
    }
}