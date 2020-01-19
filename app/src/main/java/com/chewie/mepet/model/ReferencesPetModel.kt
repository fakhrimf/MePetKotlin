package com.chewie.mepet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReferencesPetModel(
    var id:String?="",
    val image:String ="",
    val title:String="",
    val jenis:String="",
    val content:String=""
) : Parcelable