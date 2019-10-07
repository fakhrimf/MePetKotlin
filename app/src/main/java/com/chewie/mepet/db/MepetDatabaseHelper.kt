package com.chewie.mepet.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.chewie.mepet.pojo.pet_detail_profile
import com.chewie.mepet.pojo.pet_profile

class MepetDatabaseHelper(context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VER){
    //var fk_id_dp:String=""
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_DP = "Create table $DETAIL_PROFILE_TABLE " +
                "($ID_DETAIL_PROFILE Integer PRIMARY KEY AUTO_INCREMENT, $PET_NAME TEXT, $PET_TYPE TEXT,$PET_AGE Integer, $PET_WEIGHT REAL)"

        val CREATE_TABLE_PROFILE = "Create table $PROFILE_TABLE " +
                "($ID_PROFILE Integer PRIMARY KEY AUTO_INCREMENT, $FK_ID_DETAIL_PROFILE Integer,$JAM_PAGI TEXT,$JAM_SIANG TEXT,$JAM_MALAM TEXT)"

        db?.execSQL(CREATE_TABLE_DP)
        db?.execSQL(CREATE_TABLE_PROFILE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertPet(petDetailProfile: pet_detail_profile,petProfile: pet_profile):Boolean{
        val db = this.writableDatabase;
        val values = ContentValues();
        values.put(PET_NAME, petDetailProfile.pet_name)
        values.put(PET_TYPE,petDetailProfile.pet_type)
        values.put(PET_AGE,petDetailProfile.pet_age)
        values.put(PET_WEIGHT,petDetailProfile.pet_weight)
        val _success = db.insert(DETAIL_PROFILE_TABLE,null,values)


        val value = ContentValues();
        value.put(FK_ID_DETAIL_PROFILE,petDetailProfile.id_pet)
        val _suc = db.insert(PROFILE_TABLE,null,value)


        db.close()
        Log.v("InsertedID","$_success")
        Log.v("InsertedIDDetail","$_suc")
        return (Integer.parseInt("$_success")!=-1)
    }


    companion object{
        private val DB_NAME = "db_mepet";
        private val DB_VER = 1;

        private val DETAIL_PROFILE_TABLE = "detail_profile";
        private val PROFILE_TABLE= "profile";

        //Tabel Detail Profile
        private val ID_DETAIL_PROFILE = "id_detail_profile";
        private val PET_NAME = "nama_hewan";
        private val PET_TYPE = "jenis_hewan";
        private val PET_AGE = "umur_hewan";
        private val PET_WEIGHT = "berat_hewan";

        //Tabel Profile
        private val ID_PROFILE = "id_profile";
        private val FK_ID_DETAIL_PROFILE = "id_detail_profile";
        private val JAM_PAGI = "jam_pagi";
        private val JAM_SIANG = "jam_siang";
        private val JAM_MALAM = "jam_malam"

    }
}