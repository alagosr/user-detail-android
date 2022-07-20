package com.flagos.userdetail.data

import androidx.annotation.WorkerThread
import com.flagos.userdetail.data.network.UserDetailClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserDetailRepository @Inject constructor(
    private val userDetailClient: UserDetailClient,
    private val ioDispatcher: CoroutineDispatcher
) {

    @WorkerThread
    fun fetchUserDetail(onStart: () -> Unit, onComplete: () -> Unit, onError: (String?) -> Unit) = flow {
        userDetailClient.fetchUserDetail()
            .suspendOnSuccess {
                emit(data.results)
            }.onFailure {
                onError(message())
            }
    }.onStart {
        onStart()
    }.onCompletion {
        delay(300)
        onComplete()
    }.flowOn(ioDispatcher).conflate()
}