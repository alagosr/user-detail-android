package com.flagos.userdetail.data.network

import com.flagos.userdetail.domain.UserDetailItem
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface UserDetailService {

    @GET("api/")
    suspend fun fetchUserDetail() : ApiResponse<UserDetailItem>
}
