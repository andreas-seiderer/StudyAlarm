package com.seiderer.studyalarm

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.seiderer.studyalarm.helpers.Helpers
import kotlin.random.Random


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("AlarmReceiver", "onReceive")

        var intentFragment : String? = null

        if (intent?.extras != null) {
            intentFragment = intent.extras!!.getString("alarm")
        }

        val notificationTitle = context.getString(R.string.AlarmTitle)


        var notificationText = context.getString(R.string.btnMomentaryQuestionsStr)

        if (intentFragment == "Daily" )
            notificationText = context.getString(R.string.btnDailyQuestionsStr)

        val i = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        if (intentFragment != null) {
            i.putExtra("frgToLoad", intentFragment)
            Log.d("AlarmReceiver", "frgToLoad: $intentFragment")
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, context.getString(R.string.channel_id))
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setSmallIcon(R.mipmap.ic_launcher_foreground)
            .setColor(context.resources.getColor(R.color.red_108, context.theme))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(Random.nextInt(), builder.build())
        }


        // set alarms
        Helpers.setAlarm(context)

    }
}