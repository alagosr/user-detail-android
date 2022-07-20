package com.flagos.userdetail.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import com.flagos.userdetail.R
import com.flagos.userdetail.databinding.ActivityMainBinding
import com.flagos.userdetail.domain.Results
import com.flagos.userdetail.presentation.UserDetailViewModel.UserDetailState.*
import com.flagos.userdetail.presentation.adapter.UserDetailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserDetailViewModel by viewModels()
    private val adapter = UserDetailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() {
        binding.recycler.adapter = adapter
    }

    private fun setUpObservers() {
        with(viewModel) {
            fetchUserDetail()
            onUserDetailState.observe(this@MainActivity) { state -> setUiState(state) }
        }
    }

    private fun setUiState(state: UserDetailViewModel.UserDetailState) {
        when(state) {
            is OnError -> setErrorUi(state.error)
            is OnListRetrieved -> { setSuccessfulUi(state.list) }
            is OnLoading -> { setLoadingUi(state.loading) }
        }
    }

    private fun setErrorUi(error: String?) {
        changeListVisibility(GONE)
        with(binding.textFailedMessage) {
            text = String.format(getString(R.string.text_error), error)
            setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_error, 0, 0)
            visibility = VISIBLE
        }
    }

    private fun setSuccessfulUi(list: List<Results>) {
        changeListVisibility(VISIBLE)
        adapter.submitList(list)
    }

    private fun setLoadingUi(loading: Boolean) {
        if (loading) {
            binding.progress.visibility = VISIBLE
            binding.textFailedMessage.visibility = GONE
        } else {
            binding.progress.visibility = GONE
        }
    }

    private fun changeListVisibility(visibility: Int) {
        binding.recycler.visibility = visibility
    }
}