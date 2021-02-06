package com.mobapps.plantor.ui.home

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.mobapps.plantor.R
import com.mobapps.plantor.data.DateManager
import com.mobapps.plantor.data.ScheduledWorker
import com.mobapps.plantor.services.ScheduledNotificationManager
import com.mobapps.plantor.ui.login.ChangePasswordActivity
import com.mobapps.plantor.ui.login.LoginActivity
import com.mobapps.plantor.util.NotificationBroadcastReceiver
import com.mobapps.plantor.util.NotificationUtil
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var buttonLogout : Button
    private lateinit var userAuth : FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        buttonLogout = root.findViewById(R.id.home_logoutBtn)

        userAuth = FirebaseAuth.getInstance()

        buttonLogout.setOnClickListener{
            userAuth.signOut()

            startActivity(Intent(this.context, LoginActivity::class.java))

            activity?.finish()
        }

        //TODO: test notif
        root.findViewById<Button>(R.id.database_print).setOnClickListener {
//            ScheduledNotificationManager.getInstance()?.scheduleNotification(root.context, "2021-02-05 17:49:00", "Scheduled Notif", "wow you got it")

            startActivity(Intent(this.context, ChangePasswordActivity::class.java))
        }

        return root
    }

//    public fun scheduleAlarm(
//            scheduledTimeString: String?,
//            title: String?,
//            message: String?
//    ) {
//
//        val alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val alarmIntent =
//                Intent(context, NotificationBroadcastReceiver::class.java).let { intent ->
//                    intent.putExtra(ScheduledWorker.NOTIFICATION_TITLE, title)
//                    intent.putExtra(ScheduledWorker.NOTIFICATION_MESSAGE, message)
//                    PendingIntent.getBroadcast(context, 0, intent, 0)
//                }
//
//        // Parse Schedule time
//        val scheduledTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
//                .parse(scheduledTimeString!!)
//
//        scheduledTime?.let {
//            // With set(), it'll set non repeating one time alarm.
//            alarmMgr.set(
//                    AlarmManager.RTC_WAKEUP,
//                    it.time,
//                    alarmIntent
//            )
//        }
//    }
//
//    private fun showNotification(cxt: Context, title: String, message: String) {
//        NotificationUtil(cxt).showNotification(title, message)
//    }
}