package com.jhon.posts.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.model.Album
import com.jhon.posts.ui.composables.ErrorDialog
import com.jhon.posts.ui.composables.LoadingWheel
import com.jhon.posts.viewmodel.AlbumViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AlbumListScreen(
    userId: Int,
    albumViewModel: AlbumViewModel = hiltViewModel()
) {
    val status = albumViewModel.status.value
    val albums: List<Album> = albumViewModel.albumsList.value

    albumViewModel.getAlbumsByUserId(userId = userId)


    LazyColumn {
        items(albums) { album ->
            Text(text = album.title)
        }
    }

    if (status is ApiResponseStatus.Loading) {
        LoadingWheel()
    } else if (status is ApiResponseStatus.Error) {
        ErrorDialog(
            messageId = status.messageId,
            onErrorDialogDismiss = { albumViewModel.resetApiResponseStatus() })
    }
}