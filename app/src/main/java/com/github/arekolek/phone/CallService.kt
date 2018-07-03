package com.github.arekolek.phone

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.telecom.Call
import android.telecom.InCallService


class CallService : InCallService() {

    override fun onCallAdded(call: Call) {
        OngoingCall.call = call

        val intent = Intent(this, CallActivity::class.java)
            .setData(call.details.handle)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        NotificationCompat.Builder(this, NotificationChannels.DEFAULT)
            .setSmallIcon(R.drawable.ic_phone_in_talk_black_24dp)
            .setContentTitle("${call.details.callerDisplayName} calling")
            .setContentText("Tap to answer call")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .setContentIntent(pendingIntent)
            .setFullScreenIntent(pendingIntent, true)
            .build()
            .let {
                getSystemService(NotificationManager::class.java)
                    .notify(1, it)
            }
    }

    override fun onCallRemoved(call: Call) {
        OngoingCall.call = null
        getSystemService(NotificationManager::class.java)
            .cancel(1)
    }
}