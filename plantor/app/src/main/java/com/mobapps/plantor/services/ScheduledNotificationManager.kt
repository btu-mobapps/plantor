package com.mobapps.plantor.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.mobapps.plantor.data.DateManager
import com.mobapps.plantor.data.ScheduledWorker
import com.mobapps.plantor.util.NotificationBroadcastReceiver
import com.mobapps.plantor.util.NotificationUtil
import java.text.SimpleDateFormat
import java.util.*

class ScheduledNotificationManager {

    companion object {
        private var INSTANCE: ScheduledNotificationManager? = null

        public fun getInstance(): ScheduledNotificationManager? {
            if (INSTANCE == null) {
                INSTANCE = ScheduledNotificationManager()
            }

            return INSTANCE
        }
    }

    public fun scheduleNotification(
            context: Context,
            scheduledTimeString: String?,
            title: String?,
            message: String?
    ) {

        val alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent =
                Intent(context, NotificationBroadcastReceiver::class.java).let { intent ->
                    intent.putExtra(ScheduledWorker.NOTIFICATION_TITLE, title)
                    intent.putExtra(ScheduledWorker.NOTIFICATION_MESSAGE, message)
                    PendingIntent.getBroadcast(context, 0, intent, 0)
                }

        // Parse Schedule time
        val scheduledTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                .parse(scheduledTimeString!!)

        scheduledTime?.let {
            // With set(), it'll set non repeating one time alarm.
            alarmMgr.set(
                    AlarmManager.RTC_WAKEUP,
                    it.time,
                    alarmIntent
            )
        }
    }

    private fun showNotification(cxt: Context, title: String, message: String) {
        NotificationUtil(cxt).showNotification(title, message)
    }

}