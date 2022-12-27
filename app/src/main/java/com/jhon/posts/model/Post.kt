package com.jhon.posts.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val favorite: Boolean = false,
) : Parcelable, Comparable<Post> {
    override fun compareTo(other: Post): Int =
        if (this.id > other.id) {
            1
        } else {
            -1
        }

}