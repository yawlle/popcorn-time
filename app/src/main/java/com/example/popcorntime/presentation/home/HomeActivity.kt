package com.example.popcorntime.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.popcorntime.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = getColor(R.color.primary_color)

        setContentView(R.layout.activity_home)
    }
}