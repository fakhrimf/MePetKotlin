package com.chewie.mepet.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
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