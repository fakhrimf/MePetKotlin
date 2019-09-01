package com.chewie.mepet.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "t_detail_profile")
data class DetailProfileEntity(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id_detail_profile")var id_detail_profile:Int,
    @ColumnInfo(name = "nama_hewan")var nama_hewan:String,
    @ColumnInfo(name = "umur")var umur:Int,
    @ColumnInfo(name = "berat_badan")var berat_badan:Float,
    @ColumnInfo(name = "jenis_hewan")var jenis_hewan:String
)