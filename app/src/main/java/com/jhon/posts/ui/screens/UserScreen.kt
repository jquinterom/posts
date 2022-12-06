package com.jhon.posts.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserScreen(
    //onNavigateToPosts: () -> Unit,
) {
    // A surface container using the 'background' color from the theme
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn() {
                items(5) {
                    //CardHome()
                    Text(text = "texttttt")
                }
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(text = "See friends list")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    UserScreen()
}