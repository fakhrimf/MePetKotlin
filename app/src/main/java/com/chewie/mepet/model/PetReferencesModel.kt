package com.chewie.mepet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PetReferencesModel(
    var idPetReferences:String? = "",
    val jenis:String = "" ,
    val tipe:String = "",
    val contentReferences:String = ""
):Parcelable