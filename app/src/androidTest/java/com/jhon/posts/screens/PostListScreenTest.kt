package com.jhon.posts.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhon.posts.constants.FAKE_POST
import com.jhon.posts.constants.FAKE_USER
import com.jhon.posts.repositories.PostRepository
import com.jhon.posts.ui.screens.PostListScreen
import com.jhon.posts.viewmodel.PostListViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    companion object {
        private val viewModel = PostListViewModel(
            postRepository = PostRepository.FakePostRepository()
        )

        private val viewModelError = PostListViewModel(
            postRepository = PostRepository.FakePostRepositoryError()
        )

        private val viewModelSuccess = PostListViewModel(
            postRepository = PostRepository.FakePostRepositorySuccess()
        )
    }

    @Test
    fun testProgressBarShowWhenLoadingState() {
        composeTestRule.setContent {
            PostListScreen(onNavigateToPostDetail = {}, viewModel = viewModel)
        }
        composeTestRule.onNodeWithTag(testTag = "loading-wheel").assertIsDisplayed()
    }

    @Test
    fun testErrorDialogShowsIfErrorGettingPosts() {
        composeTestRule.setContent {
            PostListScreen(onNavigateToPostDetail = {}, viewModel = viewModelError)
        }
        composeTestRule.onNodeWithTag(testTag = "error-dialog").assertIsDisplayed()
    }
    
    @Test
    fun testPostListShowIfSuccessGettingPosts(){
        composeTestRule.setContent { 
            PostListScreen(onNavigateToPostDetail = {}, viewModel = viewModelSuccess)
        }

        composeTestRule.onNodeWithText(text = FAKE_POST.title).assertIsDisplayed()
    }

    @Test
    fun testUserListShowIfSuccessGettingUsers(){
        composeTestRule.setContent {
            PostListScreen(onNavigateToPostDetail = {}, viewModel = viewModelSuccess)
        }
        composeTestRule.onNodeWithText(text = FAKE_USER.name).assertIsDisplayed()
    }
}