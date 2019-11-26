package com.chewie.mepet.reminder

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import kotlinx.android.synthetic.main.fragment_reminder.*

class ReminderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reminder, container, false)
    }

    private fun showTimePicker(time: String) {
        val vm = ReminderVM()
        vm.showTimePicker(requireContext(), time, txtPagi, txtSiang, txtMalam)
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
        val sharPref:SharedPreferences = context!!.getSharedPreferences("pref",0)
        val id = sharPref!!.getInt("id",0)

        initBtn()
        showData(id)
    }

    private fun showData(id:Int){
        val db = MepetDatabaseHelper(context)
        val profile = db.getReminder(id)

        txtPagi.text = profile.jam_pagi
        txtSiang.text = profile.jam_siang
        txtMalam.text = profile.jam_malam

        if (txtPagi.text.equals("")){
            txtPagi.text = getString(R.string.addreminder)
        }
        if (txtSiang.text.equals("")){
            txtSiang.text = getString(R.string.addreminder)
        }
        if (txtMalam.text.equals("")){
            txtMalam.text = getString(R.string.addreminder)
        }
    }
}
