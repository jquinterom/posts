package com.jhon.posts.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.constants.FAKE_USER
import com.jhon.posts.model.User
import com.jhon.posts.ui.composables.ErrorDialog
import com.jhon.posts.ui.composables.LoadingWheel
import com.jhon.posts.ui.composables.PostCard
import com.jhon.posts.ui.composables.UserCard
import com.jhon.posts.viewmodel.PostListViewModel

@Composable
fun UserListScreen(
    viewModel: PostListViewModel = hiltViewModel(),
    onNavigateToAlbums: (userId: Int) -> Unit,
) {

    val status = viewModel.status.value
    val usersList: List<User> = viewModel.usersList.value

    Surface {
        LazyColumn {
            items(usersList) { user ->
                UserCard(
                    user = user,
                    onNavigateToAlbums = { onNavigateToAlbums(user.id) },
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


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    UserListScreen() {}
}