package com.chewie.mepet.profile.listPetProfile.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.pet_detail_profile
import com.chewie.mepet.utils.AlarmReceiver
import com.chewie.mepet.utils.SharedPreference

class ListProfileAdapter(private val context: Context, private val petList: List<pet_detail_profile>) :
    RecyclerView.Adapter<PetViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PetViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = petList.size

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet: pet_detail_profile = petList[position]
        holder.bind(pet)

        val sharPref = SharedPreference(context)

        val db = MepetDatabaseHelper(context)
        val profile = db.getReminder(petList[position].id_pet)

        val alarmReceiver = AlarmReceiver()

        holder.itemView.setOnClickListener {

            sharPref.setId(petList[position].id_pet)

            if (profile.jam_pagi.contains(":")) {
                sharPref.setJamPagi(profile.jam_pagi)
                alarmReceiver.scheduleNotifPagi(context)
            } else if (profile.jam_pagi == "" || profile.jam_pagi == null) {
                sharPref.setJamPagi("")
                alarmReceiver.scheduleNotifPagi(context)
            }

            if (profile.jam_pagi.contains(":")) {
                sharPref.setJamSiang(profile.jam_pagi)
                alarmReceiver.scheduleNotifSiang(context)
            } else if (profile.jam_pagi == "" || profile.jam_pagi == null) {
                sharPref.setJamSiang("")
                alarmReceiver.scheduleNotifSiang(context)
            }

            Log.d("idnya", sharPref.getId().toString())
        }
    }


}