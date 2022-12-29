package com.jhon.posts.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.model.Album
import com.jhon.posts.ui.composables.AlbumCard
import com.jhon.posts.ui.composables.ErrorDialog
import com.jhon.posts.ui.composables.LoadingWheel
import com.jhon.posts.viewmodel.AlbumViewModel

@Composable
fun AlbumListScreen(
    albumViewModel: AlbumViewModel = hiltViewModel(),
    onNavigateToPhotosList: (albumId: Int) -> Unit,
) {
    val albums: List<Album> = albumViewModel.albumsList.value
    val status = albumViewModel.status.value

    LazyColumn {
        items(albums) { album ->
            AlbumCard(
                album = album,
                onNavigateToPhotosList = { onNavigateToPhotosList(album.id) }
            )
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