package com.jhon.posts.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.capitalize
import com.jhon.posts.R
import com.jhon.posts.model.Album
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlbumCard(
    album: Album,
    onNavigateToPhotosList: (albumId: Int) -> Unit,
) {

    Card(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.margin_all_card),
                end = dimensionResource(id = R.dimen.margin_all_card)
            )
            .shadow(
                dimensionResource(id = R.dimen.shadow_card),
                spotColor = MaterialTheme.colors.onPrimary
            )
            .fillMaxWidth(),
        onClick = {
            onNavigateToPhotosList(album.id)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(id = R.dimen.margin_bottom_card)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.align(alignment = Alignment.Start),
                text = album.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            )
        }
    }

}