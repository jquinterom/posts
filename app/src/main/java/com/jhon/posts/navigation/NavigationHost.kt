package com.jhon.posts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            PostListScreen()
        }
        composable(route = Destinations.UserScreen.route) {
            UserScreen()
        }
    }
}