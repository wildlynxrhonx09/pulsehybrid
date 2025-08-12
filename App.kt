package com.pulsehybridx.app

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Firebase initializes automatically via google-services plugin
    }
}
