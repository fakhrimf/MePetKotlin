package com.chewie.mepet.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.chewie.mepet.model.pet_detail_profile
import com.chewie.mepet.model.pet_profile
import com.chewie.mepet.utils.*

class MepetDatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VER) {
    //var fk_id_dp:String=""
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_DP)
        db?.execSQL(CREATE_TABLE_PROFILE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertPet(petDetailProfile: pet_detail_profile): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(PET_NAME, petDetailProfile.pet_name)
        values.put(PET_TYPE, petDetailProfile.pet_type)
        values.put(PET_AGE, petDetailProfile.pet_age)
        values.put(PET_WEIGHT, petDetailProfile.pet_weight)
        val mSuccess = db.insert(DETAIL_PROFILE_TABLE, null, values)


        //db.close()
        Log.v("InsertedID", "$mSuccess")
        return (Integer.parseInt("$mSuccess") != -1)
    }

    fun updateReminder(petProfile: pet_profile, id: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(JAM_PAGI, petProfile.jam_pagi)
        values.put(JAM_SIANG, petProfile.jam_siang)
        values.put(JAM_MALAM, petProfile.jam_malam)

        db.update(PROFILE_TABLE, values, "$ID_DETAIL_PROFILE=$id", null)
        //db.close()
    }

    fun insertReminder(petProfile: pet_profile) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(FK_ID_DETAIL_PROFILE, petProfile.id_detail_profile)
        values.put(JAM_PAGI, petProfile.jam_pagi)
        values.put(JAM_SIANG, petProfile.jam_siang)
        values.put(JAM_MALAM, petProfile.jam_malam)

        db.insert(PROFILE_TABLE, null, values)
        //db.close()
        Log.v("Inserted Jam", values.toString())
    }

    fun getAllProfile(): List<pet_detail_profile> {
        val petList = ArrayList<pet_detail_profile>()
        val db = this.readableDatabase
        val column = arrayOf(ID_DETAIL_PROFILE, PET_NAME, PET_TYPE, PET_AGE, PET_WEIGHT)
        val cursor = db.query(DETAIL_PROFILE_TABLE, column, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val type = cursor.getString(2)
            val age = cursor.getInt(3)
            val weight = cursor.getFloat(4)

            val petProfile = pet_detail_profile()
            petProfile.id_pet = id
            petProfile.pet_name = name
            petProfile.pet_type = type
            petProfile.pet_age = age
            petProfile.pet_weight = weight
            petList.add(petProfile)
            //cursor.close()
        }
        return petList
    }

    fun getPetById(id: Int): pet_detail_profile {
        val db = this.readableDatabase
        val selectQuery = "Select * from $DETAIL_PROFILE_TABLE where $ID_DETAIL_PROFILE = $id "
        val cursor = db.rawQuery(selectQuery, null)
        val detailProfile = pet_detail_profile()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            detailProfile.id_pet = cursor.getInt(cursor.getColumnIndex(ID_DETAIL_PROFILE))
            detailProfile.pet_name = cursor.getString(cursor.getColumnIndex(PET_NAME))
            detailProfile.pet_type = cursor.getString(cursor.getColumnIndex(PET_TYPE))
            detailProfile.pet_age = cursor.getInt(cursor.getColumnIndex(PET_AGE))
            detailProfile.pet_weight = cursor.getFloat(cursor.getColumnIndex(PET_WEIGHT))
        }
        //cursor.close()

        return detailProfile
    }

    fun getReminder(id: Int?): pet_profile {
        val db = this.readableDatabase
        val selectQuery = "Select * from $PROFILE_TABLE where $FK_ID_DETAIL_PROFILE = $id"
        val cursor = db.rawQuery(selectQuery, null)
        val profile = pet_profile()

        if (cursor.count > 0) {
            cursor.moveToFirst()
            profile.id_profile = cursor.getInt(cursor.getColumnIndex(ID_PROFILE))
            profile.id_detail_profile = cursor.getInt(cursor.getColumnIndex(ID_DETAIL_PROFILE))
            profile.jam_pagi = cursor.getString(cursor.getColumnIndex(JAM_PAGI)) ?: "07:00"
            profile.jam_siang = cursor.getString(cursor.getColumnIndex(JAM_SIANG)) ?: "12:00"
            profile.jam_malam = cursor.getString(cursor.getColumnIndex(JAM_MALAM)) ?: "20:00"
        }
        //cursor.close()
        return profile
    }

    companion object {
        const val CREATE_TABLE_DP =
            "Create table $DETAIL_PROFILE_TABLE ($ID_DETAIL_PROFILE Integer PRIMARY KEY AUTOINCREMENT, $PET_NAME TEXT, $PET_TYPE TEXT,$PET_AGE Integer, $PET_WEIGHT REAL)"
        const val CREATE_TABLE_PROFILE =
            "Create table $PROFILE_TABLE ($ID_PROFILE Integer PRIMARY KEY AUTOINCREMENT, $FK_ID_DETAIL_PROFILE Integer,$JAM_PAGI TEXT,$JAM_SIANG TEXT,$JAM_MALAM TEXT)"
    }
}