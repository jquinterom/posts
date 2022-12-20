package com.jhon.posts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
            route = Destinations.PostDetailScreen.route + "/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            PostDetailScreen(
                postId = backStackEntry.arguments?.getInt("postId") ?: 0
            )
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
            route = Destinations.PhotosAlbumListScreen.route + "/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.IntType })
        ) { backStackEntry ->
            PhotosAlbumListScreen(
                albumId = backStackEntry.arguments?.getInt("albumId") ?: 0
            )
        }
        composable(
            route = Destinations.AlbumsListScreen.route + "/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            AlbumListScreen(
                userId = navBackStackEntry.arguments?.getInt("userId") ?: 0,
                onNavigateToPhotosList = { albumId: Int -> navController.navigate(Destinations.PhotosAlbumListScreen.route + "/${albumId}") },
            )
        }
    }
}