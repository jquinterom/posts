package com.jhon.posts.repository

import android.content.Context
import androidx.room.Room
import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.api.repository.PostRepository
import com.jhon.posts.constants.FAKE_POST
import com.jhon.posts.database.AppDatabase
import com.jhon.posts.model.Post
import com.jhon.posts.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class PostRepositoryTest {

    private var apiResponseStatusPosts: ApiResponseStatus<List<Post>>? = null
    private var apiResponseStatusToErrorPost: ApiResponseStatus<List<Post>>? = null
    private var apiResponseStatusToErrorUsers: ApiResponseStatus<List<User>>? = null

    companion object {
        private var postRepository: PostRepository? = null
        private var postRepositoryToError: PostRepository? = null

        private var postCollection: List<Post>? = null
        private lateinit var db: AppDatabase

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            val context = mock(Context::class.java)
            db = Room.inMemoryDatabaseBuilder(
                context, AppDatabase::class.java).build()

            postRepository = PostRepository(
                database = db,
                apiService = FakeServices.FakeApiService(),
                dispatcher = UnconfinedTestDispatcher()
            )

            postRepositoryToError = PostRepository(
                database = db,
                apiService = FakeServices.FakeApiServiceToError(),
                dispatcher = UnconfinedTestDispatcher()
            )
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            postRepository = null
            postRepositoryToError = null
        }
    }

    @Before
    fun setup() : Unit = runBlocking{
        apiResponseStatusPosts = postRepository?.getPostsCollection()
        postCollection = (apiResponseStatusPosts as ApiResponseStatus.Success).data

        apiResponseStatusToErrorPost = postRepositoryToError?.getPostsCollection()
        apiResponseStatusToErrorUsers = postRepositoryToError?.getUsersCollection()
    }


    @Test
    fun testDownloadPostsListStatusesCorrect(){
        assert(apiResponseStatusPosts is ApiResponseStatus.Success)
    }

    @Test
    fun testSizeOfCollectionIsCorrect(){
        assertEquals(2, postCollection?.size)
    }

    @Test
    fun testValidateTitlePostIsNotEmpty(){
        assertNotEquals("", postCollection?.get(0)?.title)
    }

    @Test
    fun testValidateTitleFakePostIsCorrect(){
        assertEquals(FAKE_POST.title, postCollection?.get(0)?.title)
    }

    // Error
    @Test
    fun testGetAllPostsError(){
        assert(apiResponseStatusToErrorPost is ApiResponseStatus.Error)
    }

    // user
    @Test
    fun testGetAllUsersError(){
        assert(apiResponseStatusToErrorUsers is ApiResponseStatus.Error)
    }

}