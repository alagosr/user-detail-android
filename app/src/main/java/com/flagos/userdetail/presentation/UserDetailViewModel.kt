package com.flagos.userdetail.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flagos.userdetail.data.UserDetailRepository
import com.flagos.userdetail.domain.Results
import com.flagos.userdetail.presentation.UserDetailViewModel.UserDetailState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val userDetailRepository: UserDetailRepository) : ViewModel() {

    private var _onUserDetailState = MutableLiveData<UserDetailState>()
    val onUserDetailState get() = _onUserDetailState

    fun fetchUserDetail() {
        val userDetail = userDetailRepository.fetchUserDetail(
            onStart = { _onUserDetailState.postValue(OnLoading(true)) },
            onComplete = { _onUserDetailState.postValue(OnLoading(false)) },
            onError = { _onUserDetailState.postValue(OnError(it)) }
        )

        viewModelScope.launch {
            userDetail.distinctUntilChanged().collect { list ->
                _onUserDetailState.value = OnListRetrieved(list)
            }
        }
    }

    sealed class UserDetailState {
        data class OnLoading(val loading: Boolean) : UserDetailState()
        data class OnError(val error: String?) : UserDetailState()
        data class OnListRetrieved(val list: List<Results>) : UserDetailState()
    }
}