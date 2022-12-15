package com.jhon.posts.ui.screens

import android.content.res.Resources.Theme
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhon.posts.R
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.constants.FAKE_POST
import com.jhon.posts.constants.FAKE_USER
import com.jhon.posts.model.Comment
import com.jhon.posts.model.User
import com.jhon.posts.ui.composables.CommentCard
import com.jhon.posts.ui.composables.ErrorDialog
import com.jhon.posts.ui.composables.LoadingWheel
import com.jhon.posts.viewmodel.PostDetailViewModel
import java.util.*


@OptIn(ExperimentalTextApi::class)
@Composable
fun PostDetailScreen(
    postId: Int,
    viewModel: PostDetailViewModel = hiltViewModel(),
) {
    val gradientColors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary)

    viewModel.getPostDetail(postId)
    val post = viewModel.post
    val user: User = viewModel.user.value
    val comments: MutableState<List<Comment>> = viewModel.listComments

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.margin_all_card)),
    ) {
        Row(
            modifier = Modifier.align(alignment = Alignment.Start),
        ) {
            Text(
                text = (post.value.title.uppercase()),
                //text = FAKE_POST.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_normal_spacer)))

        Text(
            text = post.value.body.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            //text = FAKE_POST.body,
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
                //text = FAKE_USER.name,
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
            ClickableText(
                modifier = Modifier
                    .align(alignment = Alignment.Start),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ),
                text = AnnotatedString(stringResource(R.string.comments)),
                onClick = {
                    viewModel.getComments(postId = postId)
                },
            )

            if (comments.value.isNotEmpty()) {
                LazyColumn {
                    items(comments.value) { comment ->
                        CommentCard(comment = comment)
                    }
                }
            }
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