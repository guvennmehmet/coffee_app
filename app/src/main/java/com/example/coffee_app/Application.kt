package com.example.coffee_app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoffeeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}