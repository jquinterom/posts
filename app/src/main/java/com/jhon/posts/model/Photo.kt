package com.jhon.posts.model

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    var thumbnailUrl: String
)