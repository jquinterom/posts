package com.jhon.posts.api

import com.jhon.posts.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): ApiResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ApiResponseStatus.Success(call())
    } catch (e: UnknownHostException) {
        ApiResponseStatus.Error(R.string.there_is_no_internet_connection)
    } catch (e: HttpException) {
        ApiResponseStatus.Error(R.string.error_connecting_server)
    }
}