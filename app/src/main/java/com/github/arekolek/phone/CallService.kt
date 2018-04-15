package com.github.arekolek.phone

import android.content.Intent
import android.telecom.Call
import android.telecom.InCallService

class CallService : InCallService() {

    override fun onCallAdded(call: Call) {
        OngoingCall.call = call

        Intent(baseContext, CallActivity::class.java)
                .setData(call.details.handle)
                .let(::startActivity)
    }

    override fun onCallRemoved(call: Call) {
        OngoingCall.call = null
    }
}