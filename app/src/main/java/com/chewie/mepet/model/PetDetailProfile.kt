package com.chewie.mepet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PetDetailProfile (
    val idPet:Int?,
    val petImage:String?,
    val petName: String,
    val petType:String,
    val petAge:Int,
    val petWeight: Float
):Parcelable
