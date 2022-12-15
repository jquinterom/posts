package com.jhon.posts.api.dto

class CommentDTO(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)