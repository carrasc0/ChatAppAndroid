package com.example.mvvmtest.model.Response

import com.example.mvvmtest.model.DiscoverUser
import com.example.mvvmtest.model.Message
import com.google.gson.annotations.SerializedName

data class DiscoverUsersResponse(
        val discoverUser: DiscoverUser) : BaseResponse()

