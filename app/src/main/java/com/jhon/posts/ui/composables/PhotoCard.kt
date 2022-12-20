package com.jhon.posts.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.jhon.posts.R
import com.jhon.posts.constants.FAKE_PHOTO

@Composable
fun PhotoCard() {
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
            modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_all_card))
        ) {
            val (image, title) = createRefs()

            Image(
                painter = rememberAsyncImagePainter(FAKE_PHOTO.thumbnailUrl),
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner_shape)))
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                contentDescription = null
            )
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
                text = "Photo Card Title"
            )
        }
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PhotoCardPreview() {
    MaterialTheme {
        PhotoCard()
    }
}