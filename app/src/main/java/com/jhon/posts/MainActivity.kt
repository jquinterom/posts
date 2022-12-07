package com.jhon.posts

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
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
        Destinations.UserScreen,
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState ) },
        drawerContent = { Drawer(scope, scaffoldState, navController, items = navigationItems) },
        drawerGesturesEnabled = true
    ){
        NavigationHost(navController)
    }
}