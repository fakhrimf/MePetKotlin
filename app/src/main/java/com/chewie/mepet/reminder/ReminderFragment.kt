package com.chewie.mepet.reminder

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.PetProfile
import com.chewie.mepet.utils.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_reminder.*
import kotlinx.android.synthetic.main.fragment_reminder.ivProfile
import java.text.SimpleDateFormat
import java.util.*

class ReminderFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reminder, container, false)
    }

    private fun initBtn() {
        ivEditPagi.setOnClickListener {
            showTimePicker(VALUE_PAGI)
        }

        txtPagi.setOnClickListener {
            showTimePicker(VALUE_PAGI)
        }

        ivEditSiang.setOnClickListener {
            showTimePicker(VALUE_SIANG)
        }

        txtSiang.setOnClickListener {
            showTimePicker(VALUE_SIANG)
        }

        ivEditMalam.setOnClickListener {
            showTimePicker(VALUE_MALAM)
        }

        txtMalam.setOnClickListener {
            showTimePicker(VALUE_MALAM)
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
        val detailProfile = db.getPetById(id)

        detailProfile?.let {
            if (!it.petImage.isNullOrBlank()){
                ivProfile.setImageBitmap(BitmapUtility.getDecodedImage("${it.petImage}"))
            }else{
                ivProfile.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_cat))
            }
        }

        txtPagi.text = profile.jamPagi
        txtSiang.text = profile.jamSiang
        txtMalam.text = profile.jamMalam

        if (txtPagi.text.isEmpty()) txtPagi.text = getString(R.string.addreminder)
        if (txtSiang.text.isEmpty()) txtSiang.text = getString(R.string.addreminder)
        if (txtMalam.text.isEmpty()) txtMalam.text = getString(R.string.addreminder)
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
            when (time) {
                VALUE_PAGI -> {
                    txtPagi.text = SimpleDateFormat("HH:mm", Locale.US).format(calendar.time)

                    val db = MepetDatabaseHelper(context)

                    val petProfile = PetProfile()
                    val profile = db.getReminder(id)
                    if (profile.jamPagi.isEmpty() && profile.jamSiang.isEmpty() && profile.jamMalam.isEmpty()) {
                        petProfile.idDetailProfile = id
                        petProfile.jamPagi = txtPagi.text.toString()
                        db.insertReminder(petProfile)
                        sharPref.setJamPagi(txtPagi.text.toString())
                    } else {
                        petProfile.jamPagi = txtPagi.text.toString()
                        petProfile.jamSiang = txtSiang.text.toString()
                        petProfile.jamMalam = txtMalam.text.toString()
                        db.updateReminder(petProfile, id)
                        sharPref.setJamPagi(txtPagi.text.toString())
                    }
                    alarmReceiver.scheduleNotifPagi(requireContext())
                    val jam = sdfJam.format(calendar.time)
                    val menit = sdfMenit.format(calendar.time)
                    Toast.makeText(context, "Reminder pagi di set untuk $jam:$menit", Toast.LENGTH_SHORT).show()
                }
                VALUE_SIANG -> {
                    txtSiang.text = SimpleDateFormat("HH:mm", Locale.US).format(calendar.time)

                    val db = MepetDatabaseHelper(context)

                    val petProfile = PetProfile()
                    val profile = db.getReminder(id)
                    if (profile.jamPagi.isEmpty() && profile.jamSiang.isEmpty() && profile.jamMalam.isEmpty()) {
                        petProfile.idDetailProfile = id
                        petProfile.jamSiang = txtSiang.text.toString()
                        db.insertReminder(petProfile)
                        sharPref.setJamSiang(txtSiang.text.toString())
                    } else {
                        petProfile.jamPagi = txtPagi.text.toString()
                        petProfile.jamSiang = txtSiang.text.toString()
                        petProfile.jamMalam = txtMalam.text.toString()
                        db.updateReminder(petProfile, id)
                        sharPref.setJamSiang(txtSiang.text.toString())
                    }
                    alarmReceiver.scheduleNotifSiang(requireContext())
                    val jam = sdfJam.format(calendar.time)
                    val menit = sdfMenit.format(calendar.time)
                    Toast.makeText(context, "Reminder siang di set untuk $jam:$menit", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    txtMalam.text = SimpleDateFormat("HH:mm", Locale.US).format(calendar.time)
                    val db = MepetDatabaseHelper(context)

                    val petProfile = PetProfile()
                    val profile = db.getReminder(id)
                    if (profile.jamPagi.isEmpty() && profile.jamSiang.isEmpty() && profile.jamMalam.isEmpty()) {
                        petProfile.idDetailProfile = id
                        petProfile.jamMalam = txtMalam.text.toString()
                        db.insertReminder(petProfile)
                        sharPref.setJamMalam(txtMalam.text.toString())
                    } else {
                        petProfile.jamPagi = txtPagi.text.toString()
                        petProfile.jamSiang = txtSiang.text.toString()
                        petProfile.jamMalam = txtMalam.text.toString()
                        db.updateReminder(petProfile, id)
                        sharPref.setJamMalam(txtMalam.text.toString())
                    }
                    alarmReceiver.scheduleNotifMalam(requireContext())
                    val jam = sdfJam.format(calendar.time)
                    val menit = sdfMenit.format(calendar.time)
                    Toast.makeText(context, "Reminder malam di set untuk $jam:$menit", Toast.LENGTH_SHORT).show()
                }
            }
        }
        TimePickerDialog(context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
    }
}
