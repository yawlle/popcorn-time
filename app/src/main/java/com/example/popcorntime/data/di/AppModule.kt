package com.example.popcorntime.data.di

import com.example.popcorntime.PopcornTimeApplication
import com.example.popcorntime.data.repository.RepositoryModule
import com.example.popcorntime.presentation.common.di.ViewModelFactoryModule
import com.example.popcorntime.presentation.common.di.ViewModelModule
import com.example.popcorntime.presentation.home.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MoviesModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        CoroutineDispatchersModule::class,
    ]
)
interface AppComponent {
    fun inject(application: PopcornTimeApplication)
    fun inject(activity: HomeActivity)
}