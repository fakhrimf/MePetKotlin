package com.chewie.mepet.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.widget.Toast
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.pet_detail_profile
import com.chewie.mepet.model.pet_profile

class AddPetVM(application: Application) : AndroidViewModel(application) {

    fun insertData(petName: String, petType: String, petAge: Int, petWeight: Float) {
        val db = MepetDatabaseHelper(getApplication())
        val pet = pet_detail_profile()
        val petProfile = pet_profile()

        pet.pet_name = petName
        pet.pet_type = petType
        pet.pet_age = petAge
        pet.pet_weight = petWeight

        petProfile.id_detail_profile = pet.id_pet
        db.insertPet(pet)
        Toast.makeText(getApplication(), "Berhasil! ", Toast.LENGTH_LONG).show()
    }
}