package com.chewie.mepet.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "t_reminder")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_reminder") var id_reminder: Int,
    @ColumnInfo(name = "jam_pagi") var jam_pagi: Time,
    @ColumnInfo(name = "jam_siang") var jam_siang: Time,
    @ColumnInfo(name = "jam_malam") var jam_malam: Time
)