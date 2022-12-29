package com.jhon.posts.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
fun PopUpPhoto(photoUrl: String, hidePopUp: () -> Unit) {
    Dialog(onDismissRequest = { hidePopUp() }) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .clickable { hidePopUp() }
                .fillMaxSize(),
            alignment = Alignment.Center,
            model = photoUrl,
            contentDescription = null,
        ) {

            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier.clickable { hidePopUp() },
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    }
}
