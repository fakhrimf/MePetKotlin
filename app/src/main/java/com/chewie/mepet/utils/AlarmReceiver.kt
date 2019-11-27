package com.chewie.mepet.utils

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.widget.Toast
import com.chewie.mepet.R
import com.chewie.mepet.Home
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    private val pcid = "primary_notification_channel"

    override fun onReceive(context: Context?, intent: Intent?) {
//        println("notification "+mNotificationManager))
//        val notification = intent?.getParcelableExtra("notification") as Notification
        val mNotificationManager: NotificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nChannel = NotificationChannel(
                pcid,
                "Reminder Notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            nChannel.enableLights(true)
            nChannel.lightColor = Color.parseColor("#F2994A")
            nChannel.enableVibration(true)
            val long = longArrayOf(0, 10, 5, 15)
            nChannel.vibrationPattern = long
            nChannel.description = "Reminds you to feed your PetFragment"
            mNotificationManager.createNotificationChannel(nChannel)
//            Toast.makeText(context, "Channel Created", Toast.LENGTH_SHORT).show()
        }
        val uniqueId = ((Date().time / 1000L) % Integer.MAX_VALUE).toInt()
        mNotificationManager.notify(uniqueId, getNotificationBuilder(context))
    }

    private fun getNotificationBuilder(context: Context): Notification {
        val intentReminder = Intent(context, Home::class.java)
//        val intentShop = Intent(context, Home::class.java)
        intentReminder.putExtra("fragment", "reminder")
        val pendingIntentReminder =
            PendingIntent.getActivity(context, 0, intentReminder, Intent.FILL_IN_ACTION)
//        val pendingIntentShop =
//            PendingIntent.getActivity(context, 0, intentShop, Intent.FILL_IN_ACTION)
//        val icon = BitmapFactory.decodeResource(context?.resources,R.drawable.ic_food)
        val time = SimpleDateFormat("HH:mm",Locale.US).format(Date())
        val notifyBuilder =
            NotificationCompat.Builder(context, "primary_notification_channel")
                .setContentTitle("Feed your Pet!")
                .setContentText("Hey it's $time, time to feed your PetFragment!")
                .setSmallIcon(R.drawable.ic_kochengoneblack)
//                .setLargeIcon(icon)
                .setContentIntent(pendingIntentReminder) //Unused due to error in emptyFrag fragment
//                .addAction(R.drawable.ic_meshop, "Shop", pendingIntentShop)
                .setAutoCancel(true)
        return notifyBuilder.build()
    }

    fun scheduleNotifPagi(context: Context) {
        val intentReminder = Intent(context, AlarmReceiver::class.java)
//        intentReminder.putExtra("notification", notif)
//        intentReminder.putExtra("reminder", "reminder")
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intentReminder,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val sharPref = SharedPreference(context)
        val jamPagi = sharPref.getJamPagi()

        if(jamPagi.contains(":")){
            val jam = jamPagi.split(":")[0]
            val menit = jamPagi.split(":")[1]

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val cal = Calendar.getInstance()
            cal.timeInMillis = System.currentTimeMillis()
            cal.set(Calendar.HOUR_OF_DAY, jam.toInt())
            cal.set(Calendar.MINUTE, menit.toInt())
            alarmManager.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                cal.timeInMillis, pendingIntent
            )
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                cal.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }
    fun scheduleNotifSiang(context: Context) {
        val intentReminder = Intent(context, AlarmReceiver::class.java)
//        intentReminder.putExtra("notification", notif)
//        intentReminder.putExtra("reminder", "reminder")
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            intentReminder,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val sharPref = SharedPreference(context)
        val jamSiang = sharPref.getJamSiang()
        if (jamSiang.contains(":")){
            val jam = jamSiang.split(":")[0]
            val menit = jamSiang.split(":")[1]

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val cal = Calendar.getInstance()
            cal.timeInMillis = System.currentTimeMillis()
            cal.set(Calendar.HOUR_OF_DAY, jam.toInt())
            cal.set(Calendar.MINUTE, menit.toInt())
            alarmManager.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                cal.timeInMillis, pendingIntent
            )
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                cal.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }

    fun scheduleNotifMalam(context: Context) {
        val intentReminder = Intent(context, AlarmReceiver::class.java)
//        intentReminder.putExtra("notification", notif)
//        intentReminder.putExtra("reminder", "reminder")
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            2,
            intentReminder,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val sharPref = SharedPreference(context)
        val jamMalam = sharPref.getJamMalam()
        if (jamMalam.contains(":")){
            val jam = jamMalam.split(":")[0]
            val menit = jamMalam.split(":")[1]

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val cal = Calendar.getInstance()
            cal.timeInMillis = System.currentTimeMillis()
            cal.set(Calendar.HOUR_OF_DAY, jam.toInt())
            cal.set(Calendar.MINUTE, menit.toInt())
            alarmManager.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                cal.timeInMillis, pendingIntent
            )
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                cal.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }
}