package com.seiderer.studyalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.seiderer.studyalarm.helpers.Helpers

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            // Set the alarm on boot.

            Helpers.setAlarm(context)
        }
    }
}