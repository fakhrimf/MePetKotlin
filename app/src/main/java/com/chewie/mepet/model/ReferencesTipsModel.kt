package com.chewie.mepet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReferencesTipsModel(
    var id:String? = "",
    val image:String = "",
    val backdrop:String = "",
    val title:String = "",
    val content:String = ""
) : Parcelable