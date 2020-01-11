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
        val petProfile = PetProfile()

        val pet = PetDetailProfile(null,petName, petType, petAge, petWeight)
//        petProfile.idDetailProfile = getId(db.getProfileAsCursor())
//        Log.d("FIND FIRST","${petProfile.idDetailProfile}")
        db.insertPet(pet)
//        Log.d("FIND LASTd","${petProfile.idDetailProfile}")
        Toast.makeText(getApplication(), "Berhasil! ", Toast.LENGTH_LONG).show()
    }

    fun getId(cursor: Cursor):Int{
        var id = 0
        while (cursor.moveToLast()){
            id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_DETAIL_PROFILE))
        }
        return id
    }
}