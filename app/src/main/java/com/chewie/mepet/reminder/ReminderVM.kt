package com.chewie.mepet.reminder

import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.widget.TextView
import android.widget.Toast
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.pet_profile
import com.chewie.mepet.utils.AlarmReceiver
import com.chewie.mepet.utils.SharedPreference
import kotlinx.android.synthetic.main.fragment_reminder.*
import java.text.SimpleDateFormat
import java.util.*

class ReminderVM {
    fun showTimePicker(context: Context, waktu: String, txtPagi: TextView, txtSiang: TextView, txtMalam: TextView) {
        val sharPref = SharedPreference(context)
        val id = sharPref.getId()

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

                    val db = MepetDatabaseHelper(context)

                    val petProfile = pet_profile()
                    val profile = db.getReminder(id)


                    if (id!=null){
                        if(profile.jam_pagi.equals("")&&profile.jam_siang.equals("")&&profile.jam_malam.equals("")){
                            petProfile.id_detail_profile = id
                            petProfile.jam_pagi = txtPagi.text.toString()
                            db.insertReminder(petProfile)
                            sharPref.setJamPagi(txtPagi.text.toString())
                        }else{
                            petProfile.jam_pagi = txtPagi.text.toString()
                            petProfile.jam_siang = txtSiang.text.toString()
                            petProfile.jam_malam = txtMalam.text.toString()
                            db.updateReminder(petProfile,id )
                            sharPref.setJamPagi(txtPagi.text.toString())
                        }
                        //Toast.makeText(context,id.toString(),Toast.LENGTH_SHORT).show()
                    }
                    alarmReceiver.scheduleNotifPagi(context)
                    val jam = sdfJam.format(time.time)
                    val menit = sdfMenit.format(time.time)
                    Toast.makeText(context, "Reminder pagi di set untuk $jam:$menit", Toast.LENGTH_SHORT).show()
                }
                "siang" -> {
                    txtSiang.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)

                    val db = MepetDatabaseHelper(context)

                    val petProfile = pet_profile()
                    val profile = db.getReminder(id)


                    if (id!=null){
                        if(profile.jam_pagi.equals("")&&profile.jam_siang.equals("")&&profile.jam_malam.equals("")){
                            petProfile.id_detail_profile = id
                            petProfile.jam_siang = txtSiang.text.toString()
                            db.insertReminder(petProfile)
                            sharPref.setJamSiang(txtSiang.text.toString())
                        }else{
                            petProfile.jam_pagi = txtPagi.text.toString()
                            petProfile.jam_siang = txtSiang.text.toString()
                            petProfile.jam_malam = txtMalam.text.toString()
                            db.updateReminder(petProfile,id)
                            sharPref.setJamSiang(txtSiang.text.toString())
                        }
                        //Toast.makeText(context,id.toString(),Toast.LENGTH_SHORT).show()
                    }
                    alarmReceiver.scheduleNotifSiang(context)
                    val jam = sdfJam.format(time.time)
                    val menit = sdfMenit.format(time.time)
                    Toast.makeText(context, "Reminder siang di set untuk $jam:$menit", Toast.LENGTH_SHORT).show()

                }
                else -> {
                    txtMalam.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
                    val db = MepetDatabaseHelper(context)

                    val petProfile = pet_profile()
                    val profile = db.getReminder(id)


                    if (id!=null){
                        if(profile.jam_pagi.equals("")&&profile.jam_siang.equals("")&&profile.jam_malam.equals("")){
                            petProfile.id_detail_profile = id
                            petProfile.jam_malam = txtMalam.text.toString()
                            db.insertReminder(petProfile)
                            sharPref.setJamMalam(txtMalam.text.toString())
                        }else{
                            petProfile.jam_pagi = txtPagi.text.toString()
                            petProfile.jam_siang = txtSiang.text.toString()
                            petProfile.jam_malam = txtMalam.text.toString()
                            db.updateReminder(petProfile,id)
                            sharPref.setJamMalam(txtMalam.text.toString())
                        }
                        //Toast.makeText(context,id.toString(),Toast.LENGTH_SHORT).show()
                    }
                    alarmReceiver.scheduleNotifMalam(context)
                    val jam = sdfJam.format(time.time)
                    val menit = sdfMenit.format(time.time)
                    Toast.makeText(context, "Reminder malam di set untuk $jam:$menit", Toast.LENGTH_SHORT).show()
                }
            }
        }
        TimePickerDialog(context, timeSetListener, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), true).show()
    }
}