package com.jhon.posts.constants

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.jhon.posts.model.*

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
const val GET_ALL_POSTS_URL = "posts"
const val GET_ALL_COMMENTS_URL = "comments"
const val GET_ALL_USERS_URL = "users"
const val GET_ALL_ALBUMS_URL = "albums"
const val GET_ALL_PHOTOS_URL = "photos"
const val GET_POST_BY_ID_URL = "$GET_ALL_POSTS_URL/{postId}"
const val GET_USER_BY_ID_URL = "$GET_ALL_USERS_URL/{userId}"
const val GET_ALL_COMMENTS_BY_POST_ID_URL = "$GET_ALL_POSTS_URL/{postId}/$GET_ALL_COMMENTS_URL"
const val GET_ALL_ALBUMS_BY_USER_ID_URL = "$GET_ALL_USERS_URL/{userId}/$GET_ALL_ALBUMS_URL"
const val GET_ALL_PHOTOS_BY_ALBUM_ID_URL = "$GET_ALL_ALBUMS_URL/{albumId}/$GET_ALL_PHOTOS_URL"


// Fake User
val FAKE_POST = Post(0, 1, "Fake post", "Content for fake post from constants")
val FAKE_USER = User(0, "Fake User", "fakeuser1", "unknown@unknown.com", "123", "www.website.com")
val FAKE_COMMENTS = listOf<Comment>(
    Comment(1, 1, "Comment 1", "comment@comment.com", LoremIpsum(10).values.first()),
    Comment(1, 2, "Comment 2", "comment1@comment.com", LoremIpsum(10).values.first()),
    Comment(1, 3, "Comment 2", "comment2@comment.com", LoremIpsum(10).values.first()),
)
val FAKE_PHOTO = Photo(
    albumId = 1,
    id = 1,
    title = "Fake title photo",
    url = "https://via.placeholder.com/600/66b7d2",
    thumbnailUrl = "https://via.placeholder.com/150/66b7d2"
)

val FAKE_ALBUM = Album(1, 1, "Fake Album")

const val DELIMITER_SPLIT = '/'
