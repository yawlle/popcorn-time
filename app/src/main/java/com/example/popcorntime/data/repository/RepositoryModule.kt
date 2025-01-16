package com.example.popcorntime.data.repository

import com.example.popcorntime.data.remote.MoviesAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepository(api: MoviesAPI): MoviesRepository {
        return MoviesRepository(api)
    }
}