package com.flagos.userdetail.data

import app.cash.turbine.test
import com.flagos.userdetail.MainCoroutinesRule
import com.flagos.userdetail.MockUtils
import com.flagos.userdetail.data.network.UserDetailClient
import com.flagos.userdetail.data.network.UserDetailService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailRepositoryTest {

    private lateinit var repository: UserDetailRepository
    private lateinit var client: UserDetailClient
    private val service: UserDetailService = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        client = UserDetailClient(service)
        repository = UserDetailRepository(client, coroutinesRule.testDispatcher)
    }

    @Test
    fun fetchEmployeeDirectoryFromNetworkTest() = runTest {
        val mockData = MockUtils.mockUserDetailItem()
        whenever(service.fetchUserDetail()).thenReturn(ApiResponse.of { Response.success(mockData) })

        repository.fetchUserDetail(onStart = {}, onError = {}, onComplete = {}).test {
            val expectItem = awaitItem()[0]
            assertEquals(expectItem.gender, "female")
            assertEquals(expectItem.name?.first, "Birgid")
            assertEquals(expectItem.login?.uuid, "27da6dc4-32dd-4c84-8cfc-2adaac95800e")
            assertEquals(expectItem, mockData.results[0])
            awaitComplete()
        }
    }
}