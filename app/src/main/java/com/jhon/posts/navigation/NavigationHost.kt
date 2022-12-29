package com.jhon.posts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jhon.posts.constants.KEY_ALBUM_ID
import com.jhon.posts.constants.KEY_POST_ID
import com.jhon.posts.constants.KEY_USER_ID
import com.jhon.posts.ui.screens.*

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.PostsListScreen.route
    ) {

        composable(
            route = Destinations.PostDetailScreen.route + "/{$KEY_POST_ID}",
            arguments = listOf(navArgument(KEY_POST_ID) { type = NavType.IntType })
        ) {
            PostDetailScreen()
        }
        composable(route = Destinations.PostsListScreen.route) {
            PostListScreen(
                onNavigateToPostDetail = { posId: Int -> navController.navigate(Destinations.PostDetailScreen.route + "/${posId}") },
            )
        }
        composable(route = Destinations.UsersListScreen.route) {
            UserListScreen(
                onNavigateToAlbums = { userId: Int -> navController.navigate(Destinations.AlbumsListScreen.route + "/${userId}") },
            )
        }

        composable(
            route = Destinations.PhotosAlbumListScreen.route + "/{$KEY_ALBUM_ID}",
            arguments = listOf(navArgument(KEY_ALBUM_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            PhotosAlbumListScreen(
                albumId = backStackEntry.arguments?.getInt(KEY_ALBUM_ID) ?: 0
            )
        }
        composable(
            route = Destinations.AlbumsListScreen.route + "/{$KEY_USER_ID}",
            arguments = listOf(navArgument(KEY_USER_ID) {
                type = NavType.IntType
            })
        ) {
            AlbumListScreen(
                onNavigateToPhotosList = { albumId: Int -> navController.navigate(Destinations.PhotosAlbumListScreen.route + "/${albumId}") },
            )
        }
    }
}