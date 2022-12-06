package com.jhon.posts.api.dto

import com.jhon.posts.model.Post

class PostDTOMapper {
    fun formPostDTOToPostDomain(postDTO: PostDTO): Post {
        return Post(
            postDTO.userId,
            postDTO.id,
            postDTO.title,
            postDTO.body
        )
    }

    fun fromPostDTOListToPostDomainList(postDTOList: List<PostDTO>): List<Post> {
        return postDTOList.map {
            formPostDTOToPostDomain(it)
        }
    }
}