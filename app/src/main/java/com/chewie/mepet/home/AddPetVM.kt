package com.chewie.mepet.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.content.Context
import android.widget.Toast
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.PetDetailProfile
import com.chewie.mepet.utils.AlarmReceiver
import com.chewie.mepet.utils.SharedPreference

class AddPetVM(application: Application) : AndroidViewModel(application) {
    val context by lazy {
        getApplication() as Context
    }

    fun insertData(petName: String,petImage:String?,petType: String, petAge: Int, petWeight: Float) {
        val db = MepetDatabaseHelper(getApplication())
        val pet = PetDetailProfile(null,petImage,petName, petType, petAge, petWeight)
        db.insertPet(pet)
        Toast.makeText(context, context.getString(R.string.berhasil), Toast.LENGTH_LONG).show()

        var id: Int? = null
        for (i in db.getAllProfile()) {
            if (i.petName == pet.petName){
                id = i.idPet
            }
        }

        val sharPref = SharedPreference(context)
        val profile = db.getReminder(id ?: 0)
        val alarmReceiver = AlarmReceiver()
        sharPref.setId(id ?: 0)

        if (profile.jamPagi.contains(":")) {
            sharPref.setJamPagi(profile.jamPagi)
            alarmReceiver.scheduleNotifPagi(context)
        } else if (profile.jamPagi.isEmpty()) {
            sharPref.setJamPagi("")
            alarmReceiver.scheduleNotifPagi(context)
        }
        if (profile.jamPagi.contains(":")) {
            sharPref.setJamSiang(profile.jamPagi)
            alarmReceiver.scheduleNotifSiang(context)
        } else if (profile.jamPagi.isEmpty()) {
            sharPref.setJamSiang("")
            alarmReceiver.scheduleNotifSiang(context)
        }
    }

    fun editData(pet: PetDetailProfile) {
        val db = MepetDatabaseHelper(getApplication())
        db.editPet(pet)
        Toast.makeText(context, context.getString(R.string.berhasil), Toast.LENGTH_LONG).show()

        var id: Int? = null
        for (i in db.getAllProfile()) {
            if (i.petName == pet.petName){
                id = i.idPet
            }
        }

        val sharPref = SharedPreference(context)
        val profile = db.getReminder(id ?: 0)
        val alarmReceiver = AlarmReceiver()
        sharPref.setId(id ?: 0)

        if (profile.jamPagi.contains(":")) {
            sharPref.setJamPagi(profile.jamPagi)
            alarmReceiver.scheduleNotifPagi(context)
        } else if (profile.jamPagi.isEmpty()) {
            sharPref.setJamPagi("")
            alarmReceiver.scheduleNotifPagi(context)
        }
        if (profile.jamPagi.contains(":")) {
            sharPref.setJamSiang(profile.jamPagi)
            alarmReceiver.scheduleNotifSiang(context)
        } else if (profile.jamPagi.isEmpty()) {
            sharPref.setJamSiang("")
            alarmReceiver.scheduleNotifSiang(context)
        }
    }
}