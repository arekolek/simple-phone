package com.github.arekolek.phone

import android.app.Application
import android.app.NotificationManager

import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        getSystemService(NotificationManager::class.java).createNotificationChannels()
    }
}
