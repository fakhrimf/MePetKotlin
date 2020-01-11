package com.chewie.mepet.db

import android.arch.persistence.room.*
import com.chewie.mepet.entity.DetailProfileEntity
import com.chewie.mepet.entity.HomeEntity
import com.chewie.mepet.entity.ProfileEntity
import com.chewie.mepet.entity.ReminderEntity
import java.sql.Time

@Dao
interface Home {
    @Query("SELECT * from t_home inner join t_profile on(t_home.id_home=t_profile.idProfile)")
    fun getAllHome(): List<HomeEntity>

    @Query("Select * from t_home inner join t_profile on(t_home.id_home=t_profile.idProfile) where idProfile=:idProfile")
    fun getHomeById(id_profile: String): List<HomeEntity>

    @Insert
    fun insertHome(home: HomeEntity)

    @Delete
    fun deleteHome(home: HomeEntity)

    @Query("Update t_home SET idProfile = :homeProfile WHERE id_home=:homeId")
    fun updateHome(homeId: Int, homeProfile: Int)
}

interface Reminder {
    @Query("Select * from t_reminder")
    fun getAllReminder(): List<ReminderEntity>

    @Insert
    fun insertReminder(reminder: ReminderEntity)

    @Query("Update t_reminder set jamPagi = :jm_pagi,jamSiang=:jm_siang,jamMalam=:jm_malam where id_reminder = :id_reminder")
    fun update(jm_pagi: Time, jm_siang: Time, jm_malam: Time, id_reminder:Int)

}

abstract class DetailProfile {
    @Query("Select * from t_detail_profile")
    abstract fun getAllDetailProfile(): List<DetailProfileEntity>

    @Delete
    abstract fun delete(detailProfile:DetailProfileEntity)

    @Query("Update t_detail_profile set nama_hewan = :nm_hewan,umur=:umur,berat_badan=:b_badan,jenis_hewan = :jen_hewan where idDetailProfile=:idDetailProfile ")
    abstract fun update(nm_hewan:String, umur:Int, b_badan:Float, jen_hewan:String, id_detail_profile:Int)

    @Transaction
    @Query("Insert into t_detail_profile(nama_hewan,umur,berat_badan,jenis_hewan) values(:nm_hewan,:umur,:b_badan,:jenis_hewan)")
    abstract fun  insertDetailProfile(nm_hewan: String,umur: Int,b_badan: Float,jenis_hewan: String)
    @Query("Insert into t_profile(idDetailProfile,jamPagi,jamSiang,jamMalam) values(LAST_INSERT_ID(),:jamPagi,:jamSiang,:jamMalam)")
    abstract fun insertIdDetailProfile(jam_pagi:Time,jam_siang:Time,jam_malam:Time)
}

interface Profile {
    @Query("Select*from t_profile inner join t_detail_profile on(t_profile.idDetailProfile=t_detail_profile.idDetailProfile) " +
            "inner join t_reminder on (t_reminder.id_reminder=t_profile.idProfile)")
    fun getAllProfile():List<ProfileEntity>

    @Insert
    @Query("insert into t_profile(jamPagi,jamSiang,jamMalam) values(:jamPagi,:jamSiang,:jamMalam)")
    fun insertProfile(jam_pagi: Time,jam_siang: Time,jam_malam: Time)

}