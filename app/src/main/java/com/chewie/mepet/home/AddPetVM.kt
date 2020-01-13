package com.chewie.mepet.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.content.Context
import android.widget.Toast
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.PetDetailProfile

class AddPetVM(application: Application) : AndroidViewModel(application) {
    val context by lazy {
        getApplication() as Context
    }

    fun insertData(petName: String, petType: String, petAge: Int, petWeight: Float) {
        val db = MepetDatabaseHelper(getApplication())
        val pet = PetDetailProfile(null,petName, petType, petAge, petWeight)
        db.insertPet(pet)
        Toast.makeText(context, context.getString(R.string.berhasil), Toast.LENGTH_LONG).show()
    }
}