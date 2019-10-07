package com.chewie.mepet

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.NotificationCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_reminder.*
import java.text.SimpleDateFormat
import java.util.*

class reminderFrag : Fragment() {
    companion object {
        fun newInstance(): reminderFrag {
            return reminderFrag()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reminder, container, false)
    }

    private fun initBtn() {
        ivEditPagi.setOnClickListener {
            showTimePicker("pagi")
        }

        txtPagi.setOnClickListener {
            showTimePicker("pagi")
        }

        ivEditSiang.setOnClickListener {
            showTimePicker("siang")
        }

        txtSiang.setOnClickListener {
            showTimePicker("siang")
        }

        ivEditMalam.setOnClickListener {
            showTimePicker("malam")
        }

        txtMalam.setOnClickListener {
            showTimePicker("malam")
        }
    }

    var jam: String = ""
    var menit: String = ""

    private fun showTimePicker(waktu: String) {
        val time = Calendar.getInstance()
        val sdfJam = SimpleDateFormat("HH", Locale.US)
        val sdfMenit = SimpleDateFormat("mm", Locale.US)
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            time.set(Calendar.HOUR_OF_DAY, hour)
            time.set(Calendar.MINUTE, minute)
            jam = sdfJam.format(time.time)
            menit = sdfMenit.format(time.time)
//            tv.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
            when (waktu) {
                "pagi" -> {
                    txtPagi.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
                    scheduleNotifPagi(jam, menit, getNotificationBuilder())
                }
                "siang" -> {
                    txtSiang.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
                    scheduleNotifSiang(jam, menit, getNotificationBuilder())
                }
                else -> {
                    txtMalam.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
                    scheduleNotifMalam(jam, menit, getNotificationBuilder())
                }
            }
        }
        TimePickerDialog(
            requireContext(),
            timeSetListener,
            time.get(Calendar.HOUR_OF_DAY),
            time.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun getNotificationBuilder(): Notification {
        val intentReminder = Intent(context, Home::class.java)
//        val intentShop = Intent(context, Home::class.java)
        intentReminder.putExtra("fragment", "reminder")
        val pendingIntentReminder =
            PendingIntent.getActivity(context, 0, intentReminder, Intent.FILL_IN_ACTION)
//        val pendingIntentShop =
//            PendingIntent.getActivity(context, 0, intentShop, Intent.FILL_IN_ACTION)
//        val icon = BitmapFactory.decodeResource(context?.resources,R.drawable.ic_food)
        val notifyBuilder =
            NotificationCompat.Builder(context as Context, "primary_notification_channel")
                .setContentTitle("Feed your Pet!")
                .setContentText("Hey it's time to feed your pet!")
                .setSmallIcon(R.drawable.ic_kochengoneblack)
//                .setLargeIcon(icon)
                .setContentIntent(pendingIntentReminder) //Unused due to error in emptyFrag fragment
//                .addAction(R.drawable.ic_meshop, "Shop", pendingIntentShop)
                .setAutoCancel(true)
        return notifyBuilder.build()
    }

    private fun scheduleNotifPagi(jam: String, menit: String, notif: Notification) {
        val intentReminder = Intent(context, AlarmReceiver::class.java)
        intentReminder.putExtra("notification", notif)
        intentReminder.putExtra("reminder", "reminder")
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intentReminder,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
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
            24 * 3600000,
            pendingIntent
        )
        Toast.makeText(context, "Reminder pagi di set untuk $jam:$menit", Toast.LENGTH_SHORT).show()
    }


    private fun scheduleNotifSiang(jam: String, menit: String, notif: Notification) {
        val intentReminder = Intent(context, AlarmReceiver::class.java)
        intentReminder.putExtra("notification", notif)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intentReminder,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
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
            24 * 3600000,
            pendingIntent
        )
        Toast.makeText(context, "Reminder siang di set untuk $jam:$menit", Toast.LENGTH_SHORT)
            .show()
    }

    private fun scheduleNotifMalam(jam: String, menit: String, notif: Notification) {
        val intentReminder = Intent(context, AlarmReceiver::class.java)
        intentReminder.putExtra("notification", notif)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intentReminder,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
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
            24 * 3600000,
            pendingIntent
        )
        Toast.makeText(context, "Reminder malam di set untuk $jam:$menit", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBtn()
    }
}
