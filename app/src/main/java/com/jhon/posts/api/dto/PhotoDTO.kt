package com.jhon.posts.api.dto

class PhotoDTO(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    var thumbnailUrl: String,
)