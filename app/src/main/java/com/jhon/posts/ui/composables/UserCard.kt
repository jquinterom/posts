package com.jhon.posts.ui.composables

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.jhon.posts.R
import com.jhon.posts.constants.FAKE_USER
import com.jhon.posts.model.User

@Composable
fun UserCard(user: User) {
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
        Column(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.margin_all_card)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    imageVector = Icons.Default.Person, contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.margin_all_card)),
                    text = user.name
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    imageVector = Icons.Default.Email, contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.margin_all_card)),
                    text = user.email
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    imageVector = Icons.Default.Phone, contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.margin_all_card)),
                    text = user.phone
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    imageVector = Icons.Default.Info, contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.margin_all_card)),
                    text = user.website
                )
            }

            ClickableText(
                modifier = Modifier.align(alignment = Alignment.End),
                text = AnnotatedString(stringResource(id = R.string.see_albums)),
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colors.secondary,
                    fontStyle = FontStyle.Italic
                ),
                onClick = {
                    Log.d("click", "click")
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewUserCard() {
    MaterialTheme {
        UserCard(user = FAKE_USER)
    }
}