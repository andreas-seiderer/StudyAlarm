package com.seiderer.studyalarm.helpers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RadioButton
import com.seiderer.studyalarm.AlarmReceiver
import java.util.*

class Helpers {
    companion object {

        fun getCheckedRadiobuttonIndex(a: Array<RadioButton>):Int {
            for (i in a.indices) {
                if (a[i].isChecked) {
                    return i
                }
            }
            return -1
        }

        /**
         * first check whether id is part of radiobuttons
         * if yes uncheck all other radiobuttons
         *
         * returns true if id was found in radiobuttons
         */
        fun justAllowOneCheckedRadiobutton(radiobuttons : Array<RadioButton>, id : Int?) : Boolean {
            for (r1 in radiobuttons) {
                if (r1.id == id) {
                    for (r2 in radiobuttons) {
                        if (r2.id != r1.id)
                            r2.isChecked = false
                    }
                    return true
                }
            }

            return false
        }

        /**
         * Set the alarm to the next daily or momentary timestamp
         */
        fun setAlarm(context : Context) : PendingIntent  {

            val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            // daily alarm has to be added in alarms!
            val alarmDaily = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, 9)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            val alarms = sortedSetOf(
                Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, 9)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                },
                Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, 12)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                },
                Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, 15)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                },
                Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, 18)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                },
                Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, 21)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
            )


            var nextAlarm : Long = 0

            if (System.currentTimeMillis() > alarms.last().timeInMillis) {
                val c =  alarms.first()
                c.add(Calendar.DATE, 1)
                nextAlarm = c.timeInMillis
            } else {
                for (a in alarms) {
                    if (System.currentTimeMillis() < a.timeInMillis) {
                        nextAlarm = a.timeInMillis

                        break
                    }
                }
            }

            if (System.currentTimeMillis() > alarmDaily.timeInMillis)
                alarmDaily.add(Calendar.DATE, 1)

            val i = Intent(context, AlarmReceiver::class.java)

            if (nextAlarm == alarmDaily.timeInMillis)
                i.putExtra("alarm", "Daily")
            else
                i.putExtra("alarm", "Momentary")

            val alarmIntent = i.let { intent ->
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)   //FLAG_IMMUTABLE
            }

            val alarmClockInfo = AlarmManager.AlarmClockInfo(nextAlarm, alarmIntent)
            alarmMgr.setAlarmClock(alarmClockInfo, alarmIntent)

            return alarmIntent
        }
    }
}