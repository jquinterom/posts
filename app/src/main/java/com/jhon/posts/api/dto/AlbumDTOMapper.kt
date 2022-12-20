package com.jhon.posts.api.dto

import com.jhon.posts.model.Album

class AlbumDTOMapper {
    private fun fromAlbumDTOToAlbumDomain(albumDTO: AlbumDTO): Album {
        return Album(
            userId = albumDTO.userId,
            id = albumDTO.id,
            title = albumDTO.title,
        )
    }

    fun fromAlbumDTOListToAlbumDomainList(albumDTOList: List<AlbumDTO>): List<Album> {
        return albumDTOList.map {
            fromAlbumDTOToAlbumDomain(it)
        }
    }
}