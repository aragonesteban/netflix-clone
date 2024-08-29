package com.example.core.data.remote.config.di

import com.example.core.data.BuildConfig
import com.example.core.data.remote.config.utils.ApiKeyInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val MOVIES_API = "https://api.themoviedb.org/3/"

@Module
@InstallIn(SingletonComponent::class)
object DataConfigModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(MOVIES_API)
            .client(providesHttpclient())
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    private fun logging(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private fun providesHttpclient(): OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(ApiKeyInterceptor(BuildConfig.API_KEY))
        .addInterceptor(logging())
        .build()
}