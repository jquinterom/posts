package com.jhon.posts.api.dto

import com.jhon.posts.model.Comment

class CommentDTOMapper {

    private fun formCommentDTOToCommentDomain(commentDTO: CommentDTO): Comment {
        return Comment(
            commentDTO.postId,
            commentDTO.id,
            commentDTO.name,
            commentDTO.email,
            commentDTO.body,
        )
    }

    fun fromCommentDTOListToCommentDomainList(commentDTOList: List<CommentDTO>): List<Comment> {
        return commentDTOList.map {
            formCommentDTOToCommentDomain(it)
        }
    }
}