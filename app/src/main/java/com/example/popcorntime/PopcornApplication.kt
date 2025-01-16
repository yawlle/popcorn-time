package com.example.popcorntime

import android.app.Application
import com.example.popcorntime.data.di.AppComponent
import com.example.popcorntime.data.di.DaggerAppComponent

class PopcornTimeApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
    }
}