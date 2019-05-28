package com.example.mvvmtest.network

import com.example.mvvmtest.model.Request.GetMessagesRequest
import com.example.mvvmtest.model.Response.GetMessagesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatInterface {

    @Headers("Content-Type: application/json")
    @POST("/getMessages")
    fun getMessages(@Body request: GetMessagesRequest): Deferred<Response<GetMessagesResponse>>

    @Headers("Content-Type: application/json")
    @POST("/getMessages")
    fun getMoreMessages(@Body request: Int): Deferred<GetMessagesResponse>


}