package com.example.popcorntime.data.di

import com.example.popcorntime.BuildConfig
import com.example.popcorntime.data.remote.MoviesAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MoviesModule {

    @Provides
    @Singleton
    fun providesMovieApi(): MoviesAPI = Retrofit.Builder()
        .baseUrl("http://www.omdbapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .addInterceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url

                    val urlWithApiKey = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", BuildConfig.OMDB_API_KEY)
                        .build()

                    val requestWithApiKey = original.newBuilder()
                        .url(urlWithApiKey)
                        .build()

                    chain.proceed(requestWithApiKey)
                }
                .build()
        )
        .build()
        .create(MoviesAPI::class.java)
}