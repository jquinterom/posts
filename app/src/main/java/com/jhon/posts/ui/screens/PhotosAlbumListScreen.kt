package com.jhon.posts.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhon.posts.ui.composables.PhotoCard
import com.jhon.posts.viewmodel.AlbumViewModel

@Composable
fun PhotosAlbumListScreen(
    albumId: Int,
    viewModel: AlbumViewModel = hiltViewModel()
) {
    viewModel.getPhotosByAlbumId(albumId = albumId)

    val photos = viewModel.photosList.value

    LazyColumn() {
        items(photos) { photo ->
            PhotoCard(photo)
        }
    }
}

