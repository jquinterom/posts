package com.jhon.posts.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhon.posts.model.Post
import com.jhon.posts.viewmodel.PostListViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostListScreen(
    viewModel: PostListViewModel = hiltViewModel(),
) {
    val status = viewModel.status.value
    val postList: List<Post> = viewModel.postList.value

    Scaffold(
        //    topBar = { PosLi}
    ) {
        LazyColumn {
            items(postList) { post ->
                Text(text = post.title)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PotListPreview() {
    PostListScreen()
}