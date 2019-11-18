package com.chewie.mepet.home

import android.content.Context
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Spinner
import android.widget.Toast
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.pet_detail_profile
import com.chewie.mepet.model.pet_profile

//ViewModel for AddPetFragment
class AddPetVM(
    private var context: Context?
) {
    fun setNpValue(np1: NumberPicker, np2: NumberPicker) {
        np1.minValue = 0
        np2.minValue = 1
        np2.maxValue = 9
        np1.maxValue = 18
    }

    fun getCbxIndex(spinner: Spinner, type: String): Int {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i) == type) {
                return i
            }
        }
        return 0
    }

    fun checkEmpty(et_petname: EditText, et_age: EditText): Boolean {
        if (et_petname.text.isEmpty()) {
            et_petname.error = "Nama Harus Diisi"
        }
        if (et_age.text.isEmpty()) {
            et_age.error = "Usia Harus Diisi"
        } else {
            return true
        }
        return false
    }

    fun insertData(petName: String, petType: String, petAge: Int, petWeight: Float) {
        val db = MepetDatabaseHelper(context)
        val pet = pet_detail_profile()
        val petProfile = pet_profile()

        pet.pet_name = petName
        pet.pet_type = petType
        pet.pet_age = petAge
        pet.pet_weight = petWeight

        petProfile.id_detail_profile = pet.id_pet
        db.insertPet(pet, petProfile)
        Toast.makeText(context, "Berhasil! ", Toast.LENGTH_LONG).show()
    }
}