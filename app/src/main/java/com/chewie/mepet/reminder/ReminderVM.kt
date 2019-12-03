package com.chewie.mepet.reminder

import android.app.TimePickerDialog
import android.content.Context
import android.widget.TextView
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.pet_profile
import com.chewie.mepet.utils.AlarmReceiver
import java.text.SimpleDateFormat
import java.util.*

class ReminderVM {
    fun showTimePicker(context: Context, waktu: String, txtPagi: TextView, txtSiang: TextView, txtMalam: TextView) {
        val time = Calendar.getInstance()
        val sdfJam = SimpleDateFormat("HH", Locale.US)
        val sdfMenit = SimpleDateFormat("mm", Locale.US)
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            time.set(Calendar.HOUR_OF_DAY, hour)
            time.set(Calendar.MINUTE, minute)
            val alarmReceiver = AlarmReceiver()
//            tv.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
            when (waktu) {
                "pagi" -> {
                    txtPagi.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
//                    scheduleNotifPagi(jam, menit, getNotificationBuilder())
                    alarmReceiver.scheduleNotifPagi(context, sdfJam.format(time.time), sdfMenit.format(time.time))

                    val db = MepetDatabaseHelper(context)
                    val petProfile = pet_profile()

                    petProfile.jam_pagi = txtPagi.text.toString()
                    db.insertReminder(petProfile)
//                    Toast.makeText(context,txtPagi.text.toString(),Toast.LENGTH_LONG).show()
                }
                "siang" -> {
                    txtSiang.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
                    alarmReceiver.scheduleNotifSiang(context, sdfJam.format(time.time), sdfMenit.format(time.time))
                }
                else -> {
                    txtMalam.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
                    alarmReceiver.scheduleNotifMalam(context, sdfJam.format(time.time), sdfMenit.format(time.time))
                }
            }
        }
        TimePickerDialog(context, timeSetListener, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), true).show()
    }
}