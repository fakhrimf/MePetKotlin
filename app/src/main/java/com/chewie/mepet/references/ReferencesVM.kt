package com.chewie.mepet.references

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.util.Log
import com.chewie.mepet.model.PetReferencesModel
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class ReferencesVM(application: Application) : AndroidViewModel(application) {
    fun getAllDataPet() {
        val database = FirebaseDatabase.getInstance().reference

        database.child("pet").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val dataPetReferences: ArrayList<PetReferencesModel> = ArrayList()

                if (dataPetReferences.size > 0)
                    dataPetReferences.clear()

                for (i in p0.children) {
                    val petReferencesModel: PetReferencesModel? = i.getValue(PetReferencesModel::class.java)
                    Log.d("ADUH_SORRY","${i.key}")
                    petReferencesModel?.let {
                        it.idPetReferences = i.key
                        dataPetReferences.add(it) }
                }

            }

            override fun onCancelled(p0: DatabaseError) {
                throw Throwable("$p0")
            }
        })
    }
}