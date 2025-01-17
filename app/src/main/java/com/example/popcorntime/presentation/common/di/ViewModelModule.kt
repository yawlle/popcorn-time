package com.example.popcorntime.presentation.common.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.popcorntime.domain.use_cases.GetMoviesByIdUseCase
import com.example.popcorntime.presentation.home.HomeViewModel
import com.example.popcorntime.presentation.moviePage.MoviePageViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
@MustBeDocumented
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsHomeViewModel(viewModel: HomeViewModel): ViewModel
}

@Module
abstract class MoviePageViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviePageViewModel::class)
    abstract fun bindMoviePageViewModel(viewModel: MoviePageViewModel): ViewModel
}

@Module
class FragmentModule {
    @Provides
    fun provideSavedStateRegistryOwner(fragment: Fragment): SavedStateRegistryOwner {
        return fragment
    }
}