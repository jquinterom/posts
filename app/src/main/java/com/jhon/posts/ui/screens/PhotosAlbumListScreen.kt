package com.jhon.posts.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
    ) {
        items(photos) { photo ->
            PhotoCard(photo)
        }
    }
}

