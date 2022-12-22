package com.jhon.posts.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object PostsListScreen : Destinations(
        route = "posts", title = "Posts", icon = Icons.Filled.List,
    )
    object UsersListScreen : Destinations(
        route = "users", title = "Users", icon = Icons.Filled.Person
    )
    object PostDetailScreen : Destinations(
        route = "postDetail", title = "Post Detail", icon = Icons.Filled.Favorite
    )
    object AlbumsListScreen : Destinations(
        route = "albums", title = "Albums", icon = Icons.Filled.Share
    )
    object PhotosAlbumListScreen : Destinations(
        route = "photos", title = "Photos", icon = Icons.Filled.Face
    )
}