package com.chewie.mepet.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.chewie.mepet.model.PetDetailProfile
import com.chewie.mepet.model.PetProfile
import com.chewie.mepet.utils.*

class MepetDatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VER) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_DP)
        db?.execSQL(CREATE_TABLE_PROFILE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertPet(petDetailProfile: PetDetailProfile): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(PET_NAME, petDetailProfile.petName)
        values.put(PET_TYPE, petDetailProfile.petType)
        values.put(PET_AGE, petDetailProfile.petAge)
        values.put(PET_WEIGHT, petDetailProfile.petWeight)
        val mSuccess = db.insert(DETAIL_PROFILE_TABLE, null, values)


        //db.close()
        Log.v("InsertedID", "$mSuccess")
        return (Integer.parseInt("$mSuccess") != -1)
    }

    fun updateReminder(petProfile: PetProfile, id: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(JAM_PAGI, petProfile.jamPagi)
        values.put(JAM_SIANG, petProfile.jamSiang)
        values.put(JAM_MALAM, petProfile.jamMalam)

        db.update(PROFILE_TABLE, values, "$ID_DETAIL_PROFILE=$id", null)
        //db.close()
    }

    fun insertReminder(petProfile: PetProfile) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(FK_ID_DETAIL_PROFILE, petProfile.idDetailProfile)
        values.put(JAM_PAGI, petProfile.jamPagi)
        values.put(JAM_SIANG, petProfile.jamSiang)
        values.put(JAM_MALAM, petProfile.jamMalam)

        db.insert(PROFILE_TABLE, null, values)
        //db.close()
        Log.v("Inserted Jam", values.toString())
    }

    fun getAllProfile(): List<PetDetailProfile> {
        val petList = ArrayList<PetDetailProfile>()
        val db = this.readableDatabase
        val column = arrayOf(ID_DETAIL_PROFILE, PET_NAME, PET_TYPE, PET_AGE, PET_WEIGHT)
        val cursor = db.query(DETAIL_PROFILE_TABLE, column, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val type = cursor.getString(2)
            val age = cursor.getInt(3)
            val weight = cursor.getFloat(4)

            val petProfile = PetDetailProfile(id, name, type, age, weight)
            petList.add(petProfile)
            //cursor.close()
        }
        return petList
    }

    fun getPetById(id: Int): PetDetailProfile? {
        val db = this.readableDatabase
        val selectQuery = "Select * from $DETAIL_PROFILE_TABLE where $ID_DETAIL_PROFILE = $id "
        val cursor = db.rawQuery(selectQuery, null)
        var detailProfile: PetDetailProfile? = null
        if (cursor.count > 0) {
            cursor.moveToFirst()
            val idPet = cursor.getInt(cursor.getColumnIndex(ID_DETAIL_PROFILE))
            val petName = cursor.getString(cursor.getColumnIndex(PET_NAME))
            val petType = cursor.getString(cursor.getColumnIndex(PET_TYPE))
            val petAge = cursor.getInt(cursor.getColumnIndex(PET_AGE))
            val petWeight = cursor.getFloat(cursor.getColumnIndex(PET_WEIGHT))
            Log.d("FIND THIS!", "IDPET: $idPet, ID: $id, NAME:$petName")
            detailProfile = PetDetailProfile(idPet, petName, petType, petAge, petWeight)
        }

        return detailProfile
    }

    fun getReminder(id: Int?): PetProfile {
        val db = this.readableDatabase
        val selectQuery = "Select * from $PROFILE_TABLE where $FK_ID_DETAIL_PROFILE = $id"
        val cursor = db.rawQuery(selectQuery, null)
        val profile = PetProfile()

        if (cursor.count > 0) {
            cursor.moveToFirst()
            profile.idProfile = cursor.getInt(cursor.getColumnIndex(ID_PROFILE))
            profile.idDetailProfile = cursor.getInt(cursor.getColumnIndex(ID_DETAIL_PROFILE))
            profile.jamPagi = cursor.getString(cursor.getColumnIndex(JAM_PAGI)) ?: "07:00"
            profile.jamSiang = cursor.getString(cursor.getColumnIndex(JAM_SIANG)) ?: "12:00"
            profile.jamMalam = cursor.getString(cursor.getColumnIndex(JAM_MALAM)) ?: "20:00"
        }
        //cursor.close()
        return profile
    }

    companion object {
        const val CREATE_TABLE_DP = "Create table $DETAIL_PROFILE_TABLE ($ID_DETAIL_PROFILE Integer PRIMARY KEY AUTOINCREMENT, $PET_NAME TEXT, $PET_TYPE TEXT,$PET_AGE Integer, $PET_WEIGHT REAL)"
        const val CREATE_TABLE_PROFILE = "Create table $PROFILE_TABLE ($ID_PROFILE Integer PRIMARY KEY AUTOINCREMENT, $FK_ID_DETAIL_PROFILE Integer,$JAM_PAGI TEXT,$JAM_SIANG TEXT,$JAM_MALAM TEXT)"
    }
}