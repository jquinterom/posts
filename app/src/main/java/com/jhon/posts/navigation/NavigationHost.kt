package com.jhon.posts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jhon.posts.ui.screens.PostDetailScreen
import com.jhon.posts.ui.screens.PostListScreen
import com.jhon.posts.ui.screens.UserScreen

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.PostsListScreen.route
    ) {
        composable(route = Destinations.PostsListScreen.route) {
            PostListScreen(
                onNavigateToPostDetail = { posId: Int -> navController.navigate(Destinations.PostDetailScreen.route + "/${posId}") },
            )
        }
        composable(route = Destinations.UserScreen.route) {
            UserScreen()
        }
        composable(
            // route = Destinations.PostDetailScreen.route + "/{postId}",
            route = Destinations.PostDetailScreen.route + "/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            PostDetailScreen(
                postId = backStackEntry.arguments?.getInt("postId") ?: 0
            )
        }
    }
}