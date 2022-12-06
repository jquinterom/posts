package com.jhon.posts.ui.composables

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jhon.posts.R
import com.jhon.posts.model.Post

@OptIn(ExperimentalTextApi::class, ExperimentalMaterialApi::class)
@Composable
fun PostCard(post: Post) {
    val gradientColors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary)

    Card(
        modifier = Modifier
            .padding(
                dimensionResource(id = R.dimen.margin_all_card)
            )
            .shadow(
                dimensionResource(id = R.dimen.shadow_card),
                spotColor = MaterialTheme.colors.onPrimary
            ),
        onClick = {}
    )
    {
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
                text = post.title,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                )
            )

            Text(
                modifier = Modifier.align(alignment = Alignment.End),
                fontStyle = FontStyle.Italic,
                fontSize = 10.sp,
                text = "Author ..."
            )
        }

    }
}

@Preview
@Composable
fun PostCardPreview() {
    PostCard(post = Post(1, 1, "Title card", "Body"))
}