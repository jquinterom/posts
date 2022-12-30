package com.jhon.posts.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhon.posts.R
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.model.Comment
import com.jhon.posts.model.Post
import com.jhon.posts.model.User
import com.jhon.posts.ui.composables.CommentCard
import com.jhon.posts.ui.composables.ErrorDialog
import com.jhon.posts.ui.composables.LoadingWheel
import com.jhon.posts.viewmodel.PostDetailViewModel
import java.util.*


@OptIn(ExperimentalTextApi::class)
@Composable
fun PostDetailScreen(
    viewModel: PostDetailViewModel = hiltViewModel(),
) {
    val gradientColors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary)

    val post = viewModel.post
    val user: User = viewModel.user.value
    val comments: MutableState<List<Comment>> = viewModel.listComments
    val statusLoadComments = viewModel.statusLoadComments.value
    val status = viewModel.status.value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.margin_detail_container)),
    ) {
        Row(
            modifier = Modifier.align(alignment = Alignment.Start),
        ) {
            Text(
                text = (post.value.title.uppercase()),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
            )
        }

        Icon(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner_shape)))
                .size(ButtonDefaults.IconSize)
                .clickable {
                },
            imageVector = if (post.value.favorite) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_normal_spacer)))

        Text(
            text = post.value.body.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            )
        )
        Row(modifier = Modifier.align(alignment = Alignment.End)) {
            Icon(
                modifier = Modifier.size(ButtonDefaults.IconSize),
                imageVector = Icons.Default.Person,
                contentDescription = null
            )

            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = user.name,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 10.sp,
            )
        }

        Column(
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.normal_margin))

        ) {
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .clickable {
                        viewModel.getComments(postId = post.value.id)
                    }
                    .fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Top),
                    text = AnnotatedString(stringResource(R.string.comments)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 10.sp,
                )

                Icon(
                    modifier = Modifier
                        .size(ButtonDefaults.IconSize)
                        .align(alignment = Alignment.Bottom),
                    imageVector = if (viewModel.showedComment.value)
                        Icons.Default.KeyboardArrowUp
                    else
                        Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                )
            }

            if (comments.value.isNotEmpty()) {
                LazyColumn {
                    items(comments.value) { comment ->
                        CommentCard(comment = comment)
                    }
                }
            }

            if (statusLoadComments is ApiResponseStatus.Loading) {
                LoadingWheel()
            } else if (statusLoadComments is ApiResponseStatus.Error) {
                ErrorDialog(
                    messageId = statusLoadComments.messageId,
                    onErrorDialogDismiss = { viewModel.resetApiResponseStatus() })
            }
        }
    }

    if (status is ApiResponseStatus.Loading) {
        LoadingWheel()
    } else if (status is ApiResponseStatus.Error) {
        ErrorDialog(
            messageId = status.messageId,
            onErrorDialogDismiss = { viewModel.resetApiResponseStatus() })
    }

}

@Preview(showBackground = true)
@Composable
fun PostDetailScreenPreview() {
    PostDetailScreen()
}