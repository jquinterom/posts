package com.jhon.posts.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.jhon.posts.R
import com.jhon.posts.constants.FAKE_PHOTO
import com.jhon.posts.model.Photo
import java.util.*

@Composable
fun PhotoCard(
    photo: Photo,
) {
    var showPopup by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.size_photo_card))
            .padding(
                dimensionResource(id = R.dimen.margin_all_card)
            )
            .shadow(
                dimensionResource(id = R.dimen.shadow_card),
                spotColor = MaterialTheme.colors.onPrimary
            ),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.margin_all_card))
                .padding(top = dimensionResource(id = R.dimen.margin_top_card))
        ) {
            val (image, title) = createRefs()

            SubcomposeAsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner_shape)))
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .clickable {
                        showPopup = !showPopup
                    },
                model = photo.thumbnailUrl,
                contentDescription = photo.title,
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                } else {
                    SubcomposeAsyncImageContent()
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dimensionResource(id = R.dimen.normal_margin))
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(image.bottom)
                        bottom.linkTo(parent.bottom)
                    },
                style = TextStyle(
                    fontSize = 14.sp, fontWeight = FontWeight.Bold
                ),
                text = photo.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                textAlign = TextAlign.Center,
            )
        }
    }

    if (showPopup) {
        PopUpPhoto(photoUrl = photo.url) { showPopup = !showPopup }
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PhotoCardPreview() {
    MaterialTheme {
        PhotoCard(FAKE_PHOTO)
    }
}

