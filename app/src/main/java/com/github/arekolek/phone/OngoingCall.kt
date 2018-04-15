package com.github.arekolek.phone

import android.telecom.Call
import android.telecom.VideoProfile
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

object OngoingCall {
    val state: BehaviorSubject<Int> = BehaviorSubject.create<Int>()

    private val callback = object : Call.Callback() {
        override fun onStateChanged(call: Call, state: Int) {
            Timber.d(call.toString())
            this@OngoingCall.state.onNext(state)
        }
    }

    var call: Call? = null
        set(value) {
            field?.unregisterCallback(callback)
            value?.let {
                it.registerCallback(callback)
                state.onNext(it.state)
            }
            field = value
        }

    fun answer() {
        call!!.answer(VideoProfile.STATE_AUDIO_ONLY)
    }

    fun hangup() {
        call!!.disconnect()
    }
}
