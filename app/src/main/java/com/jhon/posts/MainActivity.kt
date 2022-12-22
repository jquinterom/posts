package com.jhon.posts

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.jhon.posts.constants.DELIMITER_SPLIT
import com.jhon.posts.navigation.Destinations
import com.jhon.posts.navigation.Drawer
import com.jhon.posts.navigation.NavigationHost
import com.jhon.posts.navigation.TopBar
import com.jhon.posts.ui.theme.PostsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostsTheme {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )

    val scope = rememberCoroutineScope()

    val navigationItems = listOf(
        Destinations.PostsListScreen,
        Destinations.UsersListScreen,
    )

    val appName = stringResource(id = R.string.app_name)

    var title by remember { mutableStateOf(appName) }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            var currentRoute = backStackEntry.destination.route.toString().split(DELIMITER_SPLIT)[0]

            when (currentRoute) {
                Destinations.PostsListScreen.route -> {
                    currentRoute = Destinations.PostsListScreen.title
                }
                Destinations.UsersListScreen.route -> {
                    currentRoute = Destinations.UsersListScreen.title
                }
                Destinations.PostDetailScreen.route -> {
                    currentRoute = Destinations.PostDetailScreen.title
                }
                Destinations.AlbumsListScreen.route -> {
                    currentRoute = Destinations.AlbumsListScreen.title
                }
                Destinations.PhotosAlbumListScreen.route -> {
                    currentRoute = Destinations.PhotosAlbumListScreen.title
                } else -> {
                    currentRoute = appName
                }
            }

            title = currentRoute
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState, title) },
        drawerContent = { Drawer(scope, scaffoldState, navController, items = navigationItems) },
        drawerGesturesEnabled = true
    ) {
        NavigationHost(navController)
    }
}