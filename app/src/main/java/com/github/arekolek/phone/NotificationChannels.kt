package com.github.arekolek.phone

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

object NotificationChannels {
    const val DEFAULT = "default"
}

fun NotificationManager.createNotificationChannels() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Default"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(NotificationChannels.DEFAULT, name, importance)
        channel.setShowBadge(false)
        channel.enableLights(true)
        channel.enableVibration(false)
        createNotificationChannel(channel)
    }
}
