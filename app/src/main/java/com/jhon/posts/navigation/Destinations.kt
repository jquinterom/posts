package com.jhon.posts.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object PostsListScreen : Destinations(
        route = "posts/{postId}", title = "Posts", icon = Icons.Filled.List,
    )
    object UserScreen : Destinations(
        route = "users", title = "Users", icon = Icons.Filled.Person
    )
    object PostDetailScreen : Destinations(
        route = "postDetail", title = "Post Detail", icon = Icons.Filled.Favorite
    )
}