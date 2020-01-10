package com.chewie.mepet.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "t_reminder")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_reminder") var id_reminder: Int,
    @ColumnInfo(name = "jamPagi") var jam_pagi: Time,
    @ColumnInfo(name = "jamSiang") var jam_siang: Time,
    @ColumnInfo(name = "jamMalam") var jam_malam: Time
)