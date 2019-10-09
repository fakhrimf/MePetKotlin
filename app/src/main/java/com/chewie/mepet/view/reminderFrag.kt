package com.chewie.mepet.view

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.pet_profile
import com.chewie.mepet.receiver.AlarmReceiver
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
            val alarmReceiver = AlarmReceiver()
//            tv.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
            when (waktu) {
                "pagi" -> {
                    txtPagi.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
//                    scheduleNotifPagi(jam, menit, getNotificationBuilder())
                    alarmReceiver.scheduleNotifPagi(context as Context,jam,menit)

                    val db = MepetDatabaseHelper(context)
                    var success = false
                    val petProfile = pet_profile()

                    petProfile.jam_pagi = txtPagi.text.toString()
                    db.insertReminderPagi(petProfile)
//                    Toast.makeText(context,txtPagi.text.toString(),Toast.LENGTH_LONG).show()
                }
                "siang" -> {
                    txtSiang.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
                    alarmReceiver.scheduleNotifSiang(context as Context, jam, menit)
                }
                else -> {
                    txtMalam.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
                    alarmReceiver.scheduleNotifMalam(context as Context, jam, menit)
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBtn()
    }
}
