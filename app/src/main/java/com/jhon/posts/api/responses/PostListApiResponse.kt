package com.jhon.posts.api.responses

import com.jhon.posts.api.dto.PostDTO
import com.squareup.moshi.Json

class PostListApiResponse(
    val message: String,
    @field:Json(name = "is_success") val isSuccess: Boolean,
    val data: List<PostDTO>,
)