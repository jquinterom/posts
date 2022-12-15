package com.jhon.posts.constants

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.jhon.posts.model.Comment
import com.jhon.posts.model.Post
import com.jhon.posts.model.User

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
const val GET_ALL_POSTS_URL = "posts"
const val GET_ALL_COMMENTS_URL = "comment"
const val GET_ALL_USERS_URL = "users"
const val GET_POST_BY_ID_URL = "$GET_ALL_POSTS_URL/{postId}"
const val GET_USER_BY_ID_URL = "$GET_ALL_USERS_URL/{userId}"
const val GET_ALL_COMMENTS_BY_POST_ID_URL = "$GET_ALL_POSTS_URL/{postId}/comments"


// Fake User
val FAKE_POST = Post(0, 1, "Fake post", "Content for fake post from constants")
val FAKE_USER = User(0, "Fake User", "fakeuser1", "unknown@unknown.com")
val FAKE_COMMENTS = listOf<Comment>(
    Comment(1, 1, "Comment 1", "comment@comment.com", LoremIpsum(10).values.first()),
    Comment(1, 2, "Comment 2", "comment1@comment.com", LoremIpsum(10).values.first()),
    Comment(1, 3, "Comment 2", "comment2@comment.com", LoremIpsum(10).values.first()),
)

