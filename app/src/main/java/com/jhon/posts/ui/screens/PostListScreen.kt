package com.jhon.posts.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhon.posts.R
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.constants.FAKE_USER
import com.jhon.posts.model.Post
import com.jhon.posts.model.User
import com.jhon.posts.ui.composables.ErrorDialog
import com.jhon.posts.ui.composables.LoadingWheel
import com.jhon.posts.ui.composables.PostCard
import com.jhon.posts.viewmodel.PostListViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostListScreen(
    viewModel: PostListViewModel = hiltViewModel(),
) {
    val status = viewModel.status.value
    val postList: List<Post> = viewModel.postList.value
    val usersList: List<User> = viewModel.usersList.value

    Scaffold(
        topBar = { PostListScreenTopBar {} }
    ) {
        LazyColumn {
            items(postList) { post ->
                var user = FAKE_USER

                usersList.map { userItem ->
                    if(userItem.id == post.userId){
                        user = userItem
                    }
                }
                PostCard(post = post, user = user)
            }
        }
    }
    if (status is ApiResponseStatus.Loading) {
        LoadingWheel()
    } else if (status is ApiResponseStatus.Error) {
        ErrorDialog(
            messageId = status.messageId,
            onErrorDialogDismiss = { viewModel.resetApiResponseStatus() })
    }
}

@Composable
fun PostListScreenTopBar(onClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.my_post_collection)) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.surface,
        navigationIcon = { },
        elevation = dimensionResource(id = R.dimen.elevation_top_bar),
        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.margin_bottom_top_bar))
    )
}
