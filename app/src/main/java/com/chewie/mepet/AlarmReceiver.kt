package com.chewie.mepet

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    private val pcid = "primary_notification_channel"

    override fun onReceive(context: Context?, intent: Intent?) {
//        println("notification "+mNotificationManager)
        Toast.makeText(context, "Received", Toast.LENGTH_SHORT).show()
        val notification = intent?.getParcelableExtra("notification") as Notification
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
            nChannel.description = "Reminds you to feed your pet"
            mNotificationManager.createNotificationChannel(nChannel)
            Toast.makeText(context, "Channel Created", Toast.LENGTH_SHORT).show()
        }
        mNotificationManager.notify(0, notification)
        print("Notification = ")
        println(notification)
    }

}