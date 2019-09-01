package com.chewie.mepet.db

import android.arch.persistence.room.*
import com.chewie.mepet.entity.DetailProfileEntity
import com.chewie.mepet.entity.HomeEntity
import com.chewie.mepet.entity.ProfileEntity
import com.chewie.mepet.entity.ReminderEntity
import java.sql.Time

@Dao
interface Home {
    @Query("SELECT * from t_home")
    fun getAllHome(): List<HomeEntity>

    @Query("Select * from t_home where id_profile=:id_profile")
    fun getHomeById(id_profile: String): List<HomeEntity>

    @Insert
    fun insertHome(home: HomeEntity)

    @Delete
    fun deleteHome(home: HomeEntity)

    @Query("Update t_home SET id_profile = :homeProfile WHERE id_home=:homeId")
    fun updateHome(homeId: Int, homeProfile: Int)
}

interface Reminder {
    @Query("Select * from t_reminder")
    fun getAllReminder(): List<ReminderEntity>

    @Insert
    fun insertReminder(reminder: ReminderEntity)

    @Query("Update t_reminder set jam_pagi = :jm_pagi,jam_siang=:jm_siang,jam_malam=:jm_malam where id_reminder = :id_reminder")
    fun Update(jm_pagi: Time, jm_siang: Time, jm_malam: Time,id_reminder:Int)

}

interface DetailProfile {
    @Query("Select * from t_detail_profile")
    fun getAllDetailProfile(): List<DetailProfileEntity>
    @Insert
    fun insertDetailProfile(detailProfile:DetailProfileEntity)
    @Delete
    fun delete(detailProfile:DetailProfileEntity)
    @Query("Update t_detail_profile set nama_hewan = :nm_hewan,umur=:umur,berat_badan=:b_badan,jenis_hewan = :jen_hewan where id_detail_profile=:id_detail_profile ")
    fun Update(nm_hewan:String,umur:Int,b_badan:Float,jen_hewan:String,id_detail_profile:Int)
}

interface Profile {
    @Query("Select*from t_profile")
    fun getAllProfile():List<ProfileEntity>
}