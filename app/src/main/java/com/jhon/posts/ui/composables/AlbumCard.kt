package com.jhon.posts.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jhon.posts.R
import com.jhon.posts.model.Album
import java.util.*

@OptIn(ExperimentalTextApi::class, ExperimentalMaterialApi::class)
@Composable
fun AlbumCard(
    album: Album,
    onNavigateToPhotosList: (albumId: Int) -> Unit,
) {
    val gradientColors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary)
    Card(
        modifier = Modifier
            .padding(
                all = dimensionResource(id = R.dimen.margin_all_card)
            )
            .defaultMinSize(minHeight = dimensionResource(id = R.dimen.min_height_album_card))
            .shadow(
                dimensionResource(id = R.dimen.shadow_card),
                spotColor = MaterialTheme.colors.onPrimary
            )
            .fillMaxWidth(),
        onClick = {
            onNavigateToPhotosList(album.id)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(id = R.dimen.margin_bottom_card)
                ),
        ) {
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically),
                text = album.id.toString(),
                style = TextStyle(
                    fontSize = 20.sp,
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    ),
                )
            )
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = dimensionResource(id = R.dimen.margin_start_card)),
                text = album.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                )
            )
        }
    }

}