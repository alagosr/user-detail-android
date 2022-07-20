package com.flagos.userdetail.di

import com.flagos.userdetail.data.UserDetailRepository
import com.flagos.userdetail.data.network.UserDetailClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Dispatcher
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideUserDetailRepository(userDetailClient: UserDetailClient, ioDispatcher: CoroutineDispatcher) =
        UserDetailRepository(userDetailClient, ioDispatcher)
}
