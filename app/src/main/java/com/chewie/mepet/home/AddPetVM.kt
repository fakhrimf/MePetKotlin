package com.chewie.mepet.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.database.Cursor
import android.util.Log
import android.widget.Toast
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.PetDetailProfile
import com.chewie.mepet.model.PetProfile
import com.chewie.mepet.utils.ID_DETAIL_PROFILE

class AddPetVM(application: Application) : AndroidViewModel(application) {
    fun insertData(petName: String, petType: String, petAge: Int, petWeight: Float) {
        val db = MepetDatabaseHelper(getApplication())
        val pet = PetDetailProfile(null,petName, petType, petAge, petWeight)
        db.insertPet(pet)
        Toast.makeText(getApplication(), "Berhasil! ", Toast.LENGTH_LONG).show()
    }
}