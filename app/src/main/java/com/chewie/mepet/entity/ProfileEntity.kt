package com.chewie.mepet.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "t_profile",foreignKeys= [ForeignKey(entity =
DetailProfileEntity::class,parentColumns = arrayOf("idDetailProfile"),childColumns = arrayOf("idDetailProfile")
)]
)
data class ProfileEntity (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "idProfile") var id_profile:Int,
    @ColumnInfo( name = "idDetailProfile")var id_detail_profile:Int,
    @ColumnInfo(name = "jamPagi") var jam_pagi: Time,
    @ColumnInfo(name = "jamSiang") var jam_siang: Time,
    @ColumnInfo(name = "jamMalam") var jam_malam: Time
)