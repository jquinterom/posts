package com.jhon.posts.ui.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhon.posts.R
import com.jhon.posts.constants.FAKE_POST
import com.jhon.posts.constants.FAKE_USER
import com.jhon.posts.model.Post
import com.jhon.posts.model.User
import com.jhon.posts.viewmodel.PostListViewModel
import java.util.*

@OptIn(ExperimentalTextApi::class, ExperimentalMaterialApi::class)
@Composable
fun PostCard(
    post: Post,
    user: User,
    viewModel: PostListViewModel = hiltViewModel(),
    onNavigateToPostDetail: (postId: Int) -> Unit,
) {
    viewModel.getPostByIdDB(post.id)
    val gradientColors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary)
    var currentPost by remember { mutableStateOf(post) }
    val localPost by remember { mutableStateOf(viewModel.postDb) }

    Card(
        modifier = Modifier
            .padding(
                dimensionResource(id = R.dimen.margin_all_card)
            )
            .shadow(
                dimensionResource(id = R.dimen.shadow_card),
                spotColor = MaterialTheme.colors.onPrimary
            ),
        onClick = {
            onNavigateToPostDetail(post.id)
        }
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
                text = post.title.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                ),
            )

            Icon(
                modifier = Modifier
                    .constrainAs(iconFavorite) {
                        top.linkTo(parent.top)
                        bottom.linkTo(title.top)
                        end.linkTo(parent.end)
                    }
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner_shape)))
                    .size(ButtonDefaults.IconSize)
                    .clickable {
                        currentPost =
                            Post(post.userId, post.id, post.title, post.body, !currentPost.favorite)
                        viewModel. updateCurrentPost(currentPost)
                    },
                imageVector = if (currentPost.favorite) {
                    Icons.Default.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
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