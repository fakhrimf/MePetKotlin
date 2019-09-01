package com.chewie.mepet.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "t_profile",foreignKeys= arrayOf(ForeignKey(entity = ReminderEntity::class,
    parentColumns = arrayOf("id_reminder"),childColumns = arrayOf("id_reminder")), ForeignKey(entity =
    DetailProfileEntity::class,parentColumns = arrayOf("id_detail_profile"),childColumns = arrayOf("id_detail_profile")
)))
data class ProfileEntity (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id_profile") var id_profile:Int,
    @ColumnInfo(name = "id_reminder")var id_reminder:Int,
    @ColumnInfo( name = "id_detail_profile")var id_detail_profile:Int
)