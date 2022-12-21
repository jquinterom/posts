package com.jhon.posts.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
fun PopUpPhoto(photoUrl: String, hidePopUp: () -> Unit) {
    Popup(
        alignment = Alignment.Center,
        properties = PopupProperties()
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .clickable { hidePopUp() },
            model = photoUrl,
            contentDescription = null,
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    }
}