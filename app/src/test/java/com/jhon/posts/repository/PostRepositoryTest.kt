package com.jhon.posts.repository

import com.jhon.posts.api.ApiResponseStatus
import com.jhon.posts.api.repository.PostRepository
import com.jhon.posts.model.Post
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

@ExperimentalCoroutinesApi
class PostRepositoryTest {

    private var apiResponseStatus: ApiResponseStatus<List<Post>>? = null
    private var apiResponseStatusToError: ApiResponseStatus<List<Post>>? = null

    companion object {
        private var postRepository: PostRepository? = null
        private var postRepositoryToError: PostRepository? = null

        private var postCollection: List<Post>? = null

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            postRepository = PostRepository(
                apiService = FakeServices.FakeApiService(),
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
        apiResponseStatus = postRepository?.getPostsCollection()
        postCollection = (apiResponseStatus as ApiResponseStatus.Success).data

        apiResponseStatusToError = postRepositoryToError?.getPostsCollection()
    }


    @Test
    fun testDownloadPostsListStatusesCorrect(){
        assert(apiResponseStatus is ApiResponseStatus.Success)
    }

    @Test
    fun testSizeOfCollectionIsCorrect(){
        assertEquals(1, postCollection?.size)
    }

    @Test
    fun testValidateTitlePostIsNotEmpty(){
        assertNotEquals("", postCollection?.get(0)?.title)
    }

}