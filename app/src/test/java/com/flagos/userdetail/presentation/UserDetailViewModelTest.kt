package com.flagos.userdetail.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.flagos.userdetail.MainCoroutinesRule
import com.flagos.userdetail.MockUtils
import com.flagos.userdetail.data.UserDetailRepository
import com.flagos.userdetail.data.network.UserDetailClient
import com.flagos.userdetail.data.network.UserDetailService
import com.flagos.userdetail.presentation.UserDetailViewModel.UserDetailState.OnListRetrieved
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
class UserDetailViewModelTest {

    private lateinit var viewModel: UserDetailViewModel
    private lateinit var repository: UserDetailRepository
    private val service: UserDetailService = mock()
    private val client: UserDetailClient = UserDetailClient(service)

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Rule
    @JvmField
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = UserDetailRepository(client, coroutinesRule.testDispatcher)
        viewModel = UserDetailViewModel(repository)
    }

    @Test
    fun fetchEmployeeDirectoryList() = runTest {
        val mockData = MockUtils.mockUserDetailItem()
        whenever(service.fetchUserDetail()).thenReturn(ApiResponse.of { Response.success(mockData) })

        repository.fetchUserDetail(onStart = {}, onComplete = {}, onError = {}).test {
            val expectItem = awaitItem()[0]
            assertEquals(expectItem.gender, "female")
            assertEquals(expectItem.name?.first, "Birgid")
            assertEquals(expectItem.login?.uuid, "27da6dc4-32dd-4c84-8cfc-2adaac95800e")
            assertEquals(expectItem, mockData.results[0])
            awaitComplete()
        }

        viewModel.fetchUserDetail()

        assertEquals(OnListRetrieved(mockData.results), viewModel.onUserDetailState.value)
    }
}
