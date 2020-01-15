package com.chewie.mepet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReferencesTipsModel(
    val id:Int,
    val image:String,
    val title:String,
    val content:String
) : Parcelable