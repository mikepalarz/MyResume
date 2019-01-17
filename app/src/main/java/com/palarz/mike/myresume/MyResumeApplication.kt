package com.palarz.mike.myresume

import android.app.Application
import com.palarz.mike.myresume.BuildConfig.*
import timber.log.Timber

class MyResumeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Planting a tree only within debug variants
        if (DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}