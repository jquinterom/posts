package com.jhon.posts.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object PostsListScreen : Destinations(
        route = "posts", title = "Posts", icon = Icons.Filled.List
    )
    object UserScreen : Destinations(
        route = "users", title = "Users", icon = Icons.Filled.Person
    )
}