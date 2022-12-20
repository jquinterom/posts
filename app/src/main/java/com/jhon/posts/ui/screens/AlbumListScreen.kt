package com.jhon.posts.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhon.posts.model.Album
import com.jhon.posts.ui.composables.AlbumCard
import com.jhon.posts.viewmodel.AlbumViewModel

@Composable
fun AlbumListScreen(
    userId: Int,
    albumViewModel: AlbumViewModel = hiltViewModel(),
    onNavigateToPhotosList: (albumId: Int) -> Unit,
) {
    val albums: List<Album> = albumViewModel.albumsList.value
    albumViewModel.getAlbumsByUserId(userId = userId)

    LazyColumn {
        items(albums) { album ->
            AlbumCard(
                album = album,
                onNavigateToPhotosList = { onNavigateToPhotosList(album.id) }
            )
        }
    }
}