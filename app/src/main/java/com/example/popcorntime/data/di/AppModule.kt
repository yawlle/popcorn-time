package com.example.popcorntime.data.di

import com.example.popcorntime.PopcornTimeApplication
import com.example.popcorntime.data.repository.RepositoryModule
import com.example.popcorntime.presentation.common.di.FragmentModule
import com.example.popcorntime.presentation.common.di.MoviePageViewModelModule
import com.example.popcorntime.presentation.common.di.ViewModelFactoryModule
import com.example.popcorntime.presentation.common.di.ViewModelModule
import com.example.popcorntime.presentation.home.HomeFragment
import com.example.popcorntime.presentation.moviePage.MoviePageFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MoviesModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        MoviePageViewModelModule::class,
        ViewModelModule::class,
        CoroutineDispatchersModule::class,
        FragmentModule::class
    ]
)
interface AppComponent {
    fun inject(application: PopcornTimeApplication)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: MoviePageFragment)
}