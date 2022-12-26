package com.jhon.posts.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jhon.posts.R
import com.jhon.posts.constants.FAKE_POST
import com.jhon.posts.constants.FAKE_USER
import com.jhon.posts.model.Post
import com.jhon.posts.model.User
import java.util.*

@OptIn(ExperimentalTextApi::class, ExperimentalMaterialApi::class)
@Composable
fun PostCard(
    post: Post,
    user: User,
    onNavigateToPostDetail: (postId: Int) -> Unit,
) {
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
        onClick = { onNavigateToPostDetail(post.id) }
    )
    {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(id = R.dimen.margin_bottom_card)
                ),
        ) {
            val (title, iconFavorite, author) = createRefs()


            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(iconFavorite.bottom)
                        start.linkTo(parent.start)
                    },
                text = post.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                ),
            )


            Icon(
                modifier = Modifier
                    .size(ButtonDefaults.IconSize)
                    .padding(start = dimensionResource(id = R.dimen.margin_all_card))
                    .constrainAs(iconFavorite) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                imageVector = Icons.Default.Favorite,
                contentDescription = null
            )

            Text(
                modifier = Modifier
                    .constrainAs(author) {
                        top.linkTo(title.bottom)
                        end.linkTo(parent.end)
                    },
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                text = user.name,
            )
        }


    }
}

@Preview
@Composable
fun PostCardPreview() {
    PostCard(
        FAKE_POST,
        FAKE_USER
    ) {}
}