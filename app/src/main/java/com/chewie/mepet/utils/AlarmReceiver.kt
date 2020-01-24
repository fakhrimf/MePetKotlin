package com.chewie.mepet.utils

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationCompat
import com.chewie.mepet.Home
import com.chewie.mepet.R
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val mNotificationManager: NotificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nChannel = NotificationChannel(
                PCID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            nChannel.enableLights(true)
            nChannel.lightColor = context.getColor(R.color.colorPrimary)
            nChannel.enableVibration(true)
            val long = longArrayOf(0, 10, 5, 15)
            nChannel.vibrationPattern = long
            nChannel.description = CHANNEL_DESC
            mNotificationManager.createNotificationChannel(nChannel)
//            Toast.makeText(context, "Channel Created", Toast.LENGTH_SHORT).show()
        }
        val uniqueId = ((Date().time / 1000L) % Integer.MAX_VALUE).toInt()
        mNotificationManager.notify(uniqueId, getNotificationBuilder(context))
    }

    private fun getNotificationBuilder(context: Context): Notification {
        val intentReminder = Intent(context, Home::class.java)
        intentReminder.putExtra(FRAGMENT_INTENT_KEY, "reminder")
        val pendingIntentReminder =
            PendingIntent.getActivity(context, 0, intentReminder, Intent.FILL_IN_ACTION)
        val time = SimpleDateFormat("HH:mm", Locale.US).format(Date())
        val notifyBuilder =
            NotificationCompat.Builder(context, PCID)
                .setContentTitle("Feed your Pet!")
                .setContentText("Hey it's $time, time to feed your Pet")
                .setSmallIcon(R.drawable.ic_kochengoneblack)
                .setContentIntent(pendingIntentReminder)
                .setAutoCancel(true)
        return notifyBuilder.build()
    }

    fun scheduleNotifPagi(context: Context) {
        val intentReminder = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intentReminder,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val sharPref = SharedPreference(context)
        val jamPagi = sharPref.getJamPagi()

        if (jamPagi.contains(":")) {
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
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            intentReminder,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val sharPref = SharedPreference(context)
        val jamSiang = sharPref.getJamSiang()
        if (jamSiang.contains(":")) {
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
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            2,
            intentReminder,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val sharPref = SharedPreference(context)
        val jamMalam = sharPref.getJamMalam()
        if (jamMalam.contains(":")) {
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