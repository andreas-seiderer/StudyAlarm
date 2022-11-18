package com.seiderer.studyalarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.seiderer.studyalarm.helpers.Helpers
import com.seiderer.studyalarm.room.AppDatabase

class MainActivity : AppCompatActivity() {

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppDatabase.getInstance(this)

        createNotificationChannel()

        alarmMgr = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = setAlarm()



        // open correct fragment if coming from alarm notification
        if (intent.extras != null) {
            val intentFragment = intent.extras!!.getString("frgToLoad")

            if (intentFragment == "Momentary") {
                val navHostFragment =
                    this.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_startFragment_to_momentaryFragment)
            } else if (intentFragment == "Daily") {
                val navHostFragment =
                    this.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_startFragment_to_dailyFragment)
            }
        }
    }


    private fun setAlarm() : PendingIntent {

        val pintent = Helpers.setAlarm(this)

        // activate on boot alarm
        val receiver = ComponentName(this, BootReceiver::class.java)

        this.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        return pintent
    }


    /*
    fun cancelAlarm() {
        // If the alarm has been set, cancel it.
        alarmMgr?.cancel(alarmIntent)

        // cancel on boot alarm

        val receiver = ComponentName(this, BootReceiver::class.java)

        this.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }
    */

    private fun createNotificationChannel() {
        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(getString(R.string.channel_id), name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}