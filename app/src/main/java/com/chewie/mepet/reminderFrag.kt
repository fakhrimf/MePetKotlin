package com.chewie.mepet

import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        txtPagi.setOnClickListener{
            showTimePicker("pagi")
        }

        ivEditSiang.setOnClickListener {
            showTimePicker("siang")
        }

        txtSiang.setOnClickListener{
            showTimePicker("siang")
        }

        ivEditMalam.setOnClickListener {
            showTimePicker("malam")
        }

        txtMalam.setOnClickListener {
            showTimePicker("malam")
        }
    }

    private fun showTimePicker(waktu: String) {
        val time = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            time.set(Calendar.HOUR_OF_DAY, hour)
            time.set(Calendar.MINUTE, minute)
//            tv.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
            if (waktu == "pagi") {
                txtPagi.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
            } else if (waktu == "siang") {
                txtSiang.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
            } else {
                txtMalam.text = SimpleDateFormat("HH:mm", Locale.US).format(time.time)
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
