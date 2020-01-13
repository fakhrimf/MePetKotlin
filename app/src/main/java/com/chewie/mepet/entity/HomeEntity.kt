package com.chewie.mepet.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "t_home",foreignKeys = [ForeignKey(entity = ProfileEntity::class,
    parentColumns = arrayOf("idProfile"),childColumns = arrayOf("idProfile"))]
)
data class HomeEntity (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name="id_home") var id_home:Int,
    @ColumnInfo(name = "idProfile") var id_profile:Int
)
