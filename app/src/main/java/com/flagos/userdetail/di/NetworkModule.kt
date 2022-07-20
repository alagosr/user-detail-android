package com.flagos.userdetail.di

import com.flagos.userdetail.data.network.UserDetailClient
import com.flagos.userdetail.data.network.UserDetailService
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://randomuser.me/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDetailService(retrofit: Retrofit): UserDetailService = retrofit.create(UserDetailService::class.java)

    @Provides
    @Singleton
    fun provideUserDetailClient(userDetailService: UserDetailService): UserDetailClient {
        return UserDetailClient(userDetailService)
    }
}
