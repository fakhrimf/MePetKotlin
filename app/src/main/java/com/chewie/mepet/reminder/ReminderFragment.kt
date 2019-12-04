package com.chewie.mepet.reminder

import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.pet_profile
import com.chewie.mepet.utils.AlarmReceiver
import com.chewie.mepet.utils.SharedPreference
import kotlinx.android.synthetic.main.fragment_reminder.*
import java.text.SimpleDateFormat
import java.util.*

class ReminderFragment : Fragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sharPref = SharedPreference(requireContext())
        val id = sharPref.getId()

        initBtn()
        showData(id)
    }

    private fun showData(id: Int) {
        val db = MepetDatabaseHelper(context)
        val profile = db.getReminder(id)

        txtPagi.text = profile.jam_pagi
        txtSiang.text = profile.jam_siang
        txtMalam.text = profile.jam_malam

        if (txtPagi.text == "") {
            txtPagi.text = getString(R.string.addreminder)
        }
        if (txtSiang.text == "") {
            txtSiang.text = getString(R.string.addreminder)
        }
        if (txtMalam.text == "") {
            txtMalam.text = getString(R.string.addreminder)
        }
    }


    private fun showTimePicker(time: String) {
        val sharPref = SharedPreference(requireContext())
        val id = sharPref.getId()

        val calendar = Calendar.getInstance()
        val sdfJam = SimpleDateFormat("HH", Locale.US)
        val sdfMenit = SimpleDateFormat("mm", Locale.US)
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            val alarmReceiver = AlarmReceiver()
//            tv.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
            when (time) {
                "pagi" -> {
                    txtPagi.text = SimpleDateFormat("HH:mm", Locale.US).format(calendar.time)

                    val db = MepetDatabaseHelper(context)

                    val petProfile = pet_profile()
                    val profile = db.getReminder(id)
                    //Toast.makeText(context,id.toString(),Toast.LENGTH_SHORT).show()
                    if (profile.jam_pagi == "" && profile.jam_siang == "" && profile.jam_malam == "") {
                        petProfile.id_detail_profile = id
                        petProfile.jam_pagi = txtPagi.text.toString()
                        db.insertReminder(petProfile)
                        sharPref.setJamPagi(txtPagi.text.toString())
                    } else {
                        petProfile.jam_pagi = txtPagi.text.toString()
                        petProfile.jam_siang = txtSiang.text.toString()
                        petProfile.jam_malam = txtMalam.text.toString()
                        db.updateReminder(petProfile, id)
                        sharPref.setJamPagi(txtPagi.text.toString())
                    }
                    alarmReceiver.scheduleNotifPagi(requireContext())
                    val jam = sdfJam.format(calendar.time)
                    val menit = sdfMenit.format(calendar.time)
                    Toast.makeText(context, "Reminder pagi di set untuk $jam:$menit", Toast.LENGTH_SHORT)
                        .show()
                }
                "siang" -> {
                    txtSiang.text = SimpleDateFormat("HH:mm", Locale.US).format(calendar.time)

                    val db = MepetDatabaseHelper(context)

                    val petProfile = pet_profile()
                    val profile = db.getReminder(id)
                    //Toast.makeText(context,id.toString(),Toast.LENGTH_SHORT).show()
                    if (profile.jam_pagi == "" && profile.jam_siang == "" && profile.jam_malam == "") {
                        petProfile.id_detail_profile = id
                        petProfile.jam_siang = txtSiang.text.toString()
                        db.insertReminder(petProfile)
                        sharPref.setJamSiang(txtSiang.text.toString())
                    } else {
                        petProfile.jam_pagi = txtPagi.text.toString()
                        petProfile.jam_siang = txtSiang.text.toString()
                        petProfile.jam_malam = txtMalam.text.toString()
                        db.updateReminder(petProfile, id)
                        sharPref.setJamSiang(txtSiang.text.toString())
                    }
                    alarmReceiver.scheduleNotifSiang(requireContext())
                    val jam = sdfJam.format(calendar.time)
                    val menit = sdfMenit.format(calendar.time)
                    Toast.makeText(context, "Reminder siang di set untuk $jam:$menit", Toast.LENGTH_SHORT)
                        .show()

                }
                else -> {
                    txtMalam.text = SimpleDateFormat("HH:mm", Locale.US).format(calendar.time)
                    val db = MepetDatabaseHelper(context)

                    val petProfile = pet_profile()
                    val profile = db.getReminder(id)
                    //Toast.makeText(context,id.toString(),Toast.LENGTH_SHORT).show()
                    if (profile.jam_pagi == "" && profile.jam_siang == "" && profile.jam_malam == "") {
                        petProfile.id_detail_profile = id
                        petProfile.jam_malam = txtMalam.text.toString()
                        db.insertReminder(petProfile)
                        sharPref.setJamMalam(txtMalam.text.toString())
                    } else {
                        petProfile.jam_pagi = txtPagi.text.toString()
                        petProfile.jam_siang = txtSiang.text.toString()
                        petProfile.jam_malam = txtMalam.text.toString()
                        db.updateReminder(petProfile, id)
                        sharPref.setJamMalam(txtMalam.text.toString())
                    }
                    alarmReceiver.scheduleNotifMalam(requireContext())
                    val jam = sdfJam.format(calendar.time)
                    val menit = sdfMenit.format(calendar.time)
                    Toast.makeText(context, "Reminder malam di set untuk $jam:$menit", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        TimePickerDialog(context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
    }
}
