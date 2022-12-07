package com.jhon.posts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhon.posts.R
import com.jhon.posts.constants.FAKE_POST
import com.jhon.posts.model.Post
import com.jhon.posts.viewmodel.PostDetailViewModel


@OptIn(ExperimentalTextApi::class)
@Composable
fun PostDetailScreen(
    postId: Int,
    viewModel: PostDetailViewModel = hiltViewModel(),
) {
    val status = viewModel.status.value
    val post: Post = viewModel.post.value

    viewModel.setPostId(postId)
    viewModel.getPostDetail()

    val gradientColors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.margin_all_card)),
        ) {
        Row(
            modifier = Modifier.align(alignment = Alignment.Start),
        ) {
            Text(
                //text = (post?.title + " - " + user?.name),
                text = post.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            Text(
                // text = user?.name ?: FAKE_USER.name,
                text = "Name",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_normal_spacer)))

        Text(
            modifier = Modifier.align(alignment = Alignment.Start),
            text = post.body,
            //text = FAKE_POST.body,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            )
        )

        Column(
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.normal_margin))
        ) {
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.Start),
                text = stringResource(R.string.comments),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
            )

            /*
            LazyColumn {
                items(comments ?: emptyList()) { comment ->
                    CommentCard(comment = comment)
                }
            }
             */
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostDetailScreenPreview() {
    PostDetailScreen(
        FAKE_POST.id
    )
}