package com.jhon.posts.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
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
    onNavigateToPostDetail: (postId: Int) -> Unit,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    val status = viewModel.status.value
    val postList: List<Post> = viewModel.postList
    val usersList: List<User> = viewModel.usersList.value

    DisposableEffect(lifecycleOwner){
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.getPostCollection()
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold() {
        LazyColumn {
            items(postList) { post ->
                var user = FAKE_USER

                usersList.map { userItem ->
                    if (userItem.id == post.userId) {
                        user = userItem
                    }
                }
                PostCard(
                    post = post,
                    user = user,
                    setFavorite = { viewModel.updatePostDB(post = post) },
                    onNavigateToPostDetail = { onNavigateToPostDetail(post.id) }
                )
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
