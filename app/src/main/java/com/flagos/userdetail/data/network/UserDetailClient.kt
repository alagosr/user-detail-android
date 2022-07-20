package com.flagos.userdetail.data.network

import javax.inject.Inject

class UserDetailClient @Inject constructor(private val userDetailService: UserDetailService) {

    suspend fun fetchUserDetail() = userDetailService.fetchUserDetail()
}