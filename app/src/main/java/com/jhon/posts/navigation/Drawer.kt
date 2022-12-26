package com.jhon.posts.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.jhon.posts.R

@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    items: List<Destinations>
) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.ic_banner_foreground),
            contentDescription = null,
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .fillMaxSize()
                .background(color = MaterialTheme.colors.secondary),
            contentScale = ContentScale.FillWidth
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_normal_spacer))
        )

        val currentRoute = currentRoute(navController = navController)
        items.forEach { item ->
            DrawerItem(item = item, selected = currentRoute == item.route) {
                navController.navigate(item.route) {
                    launchSingleTop = true
                }

                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    }
}

@Composable
fun DrawerItem(
    item: Destinations,
    selected: Boolean,
    onItemClick: (Destinations) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(15))
            //.background(if (selected) Color.Blue.copy(alpha = 0.25f) else Color.Transparent)
            .background(if (selected)  MaterialTheme.colors.surface.copy(alpha = 0.25f) else Color.Transparent)
            .padding(8.dp)
            .clickable { onItemClick(item) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = item.icon,
            contentDescription = item.title,
            tint = if (selected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_normal_spacer)))
        Text(
            text = item.title, style = TextStyle(fontSize = 18.sp),
            color = if (selected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
        )
    }
}