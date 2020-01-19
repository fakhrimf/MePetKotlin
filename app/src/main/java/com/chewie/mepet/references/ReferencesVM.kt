package com.chewie.mepet.references

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.chewie.mepet.model.ReferencesPetModel
import com.chewie.mepet.model.ReferencesTipsModel
import com.google.firebase.database.*
import kotlin.collections.ArrayList

class ReferencesVM(application: Application) : AndroidViewModel(application) {
    var petLiveData = MutableLiveData<ArrayList<ReferencesPetModel>>()

    fun getLiveDataPet() : MutableLiveData<ArrayList<ReferencesPetModel>>{
        petLiveData.postValue(getAllDataPet())
        return petLiveData
    }

    fun getAllDataPet() : ArrayList<ReferencesPetModel> {
        val database = FirebaseDatabase.getInstance().reference
        val dataPetReferences = ArrayList<ReferencesPetModel>()

        database.child("pet").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

                if (dataPetReferences.size > 0)
                    dataPetReferences.clear()

                for (i in p0.children) {
                    val petReferencesModel: ReferencesPetModel? = i.getValue(ReferencesPetModel::class.java)
                    Log.d("ADUH_SORRY","${i.key}")
                    petReferencesModel?.let {
                        it.id = i.key
                        dataPetReferences.add(it) }
                }

            }

            override fun onCancelled(p0: DatabaseError) {
                throw Throwable("$p0")
            }
        })
        return dataPetReferences
    }

//    fun referencesPetData() : ArrayList<ReferencesPetModel> {
//        val list = ArrayList<ReferencesPetModel>()
//        for(i in 0 until 10) {
//            list.add(ReferencesPetModel(i,"","Test $i",""))
//        }
//        return list
//    }

    fun referencesTipsData() : ArrayList<ReferencesTipsModel> {
        val list = ArrayList<ReferencesTipsModel>()
        for(i in 0 until 10) {
            list.add(ReferencesTipsModel(i,"","Test $i",""))
        }
        return list
    }
}


