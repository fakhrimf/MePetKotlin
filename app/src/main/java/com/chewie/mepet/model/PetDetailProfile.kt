package com.chewie.mepet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PetDetailProfile (
    var idPet:Int?,
    var petName: String,
    var petType:String,
    var petAge:Int,
    var petWeight: Float
):Parcelable
