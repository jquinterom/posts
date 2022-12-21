package com.jhon.posts.ui.composables

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.constraintlayout.compose.ConstraintLayout
import coil.ImageLoader
import coil.compose.*
import com.jhon.posts.R
import com.jhon.posts.constants.FAKE_PHOTO
import com.jhon.posts.model.Photo

@Composable
fun PhotoCard(
    photo: Photo
) {

    Card(
        modifier = Modifier
            .padding(
                dimensionResource(id = R.dimen.margin_all_card)
            )
            .shadow(
                dimensionResource(id = R.dimen.shadow_card),
                spotColor = MaterialTheme.colors.onPrimary
            )
            .fillMaxWidth(),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.margin_all_card))
                .defaultMinSize(minHeight = dimensionResource(id = R.dimen.min_height_photo_card))
        ) {
            val (image, title) = createRefs()


            SubcomposeAsyncImage(
                modifier = Modifier
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .clickable {
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
                        start.linkTo(image.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                style = TextStyle(
                    MaterialTheme.colors.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                text = photo.title
            )

        }
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PhotoCardPreview() {
    MaterialTheme {
        PhotoCard(FAKE_PHOTO)
    }
}
