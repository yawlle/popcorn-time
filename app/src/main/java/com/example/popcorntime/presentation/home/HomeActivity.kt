package com.example.popcorntime.presentation.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.popcorntime.PopcornTimeApplication
import com.example.popcorntime.data.di.DaggerAppComponent
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeViewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as PopcornTimeApplication).appComponent?.inject(this)

        setContent {
            HomeScreen(viewModel = homeViewModel)
        }
    }
}