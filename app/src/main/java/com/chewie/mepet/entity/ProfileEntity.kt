package com.chewie.mepet.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "t_profile",foreignKeys= arrayOf(ForeignKey(entity =
    DetailProfileEntity::class,parentColumns = arrayOf("id_detail_profile"),childColumns = arrayOf("id_detail_profile")
)))
data class ProfileEntity (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id_profile") var id_profile:Int,
    @ColumnInfo( name = "id_detail_profile")var id_detail_profile:Int,
    @ColumnInfo(name = "jam_pagi") var jam_pagi: Time,
    @ColumnInfo(name = "jam_siang") var jam_siang: Time,
    @ColumnInfo(name = "jam_malam") var jam_malam: Time
)