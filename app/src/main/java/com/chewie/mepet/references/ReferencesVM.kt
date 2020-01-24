package com.chewie.mepet.references

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.chewie.mepet.model.ReferencesPetModel
import com.chewie.mepet.model.ReferencesTipsModel
import com.chewie.mepet.utils.REF_NAME_TIPS
import com.google.firebase.database.*
import kotlin.collections.ArrayList

class ReferencesVM(application: Application) : AndroidViewModel(application) {
    val petLiveData: MutableLiveData<ArrayList<ReferencesPetModel>> by lazy {
        MutableLiveData<ArrayList<ReferencesPetModel>>()
    }

    val tipsLiveData:MutableLiveData<ArrayList<ReferencesTipsModel>> by lazy{
        MutableLiveData<ArrayList<ReferencesTipsModel>>()
    }

    fun getAllDataPet() {
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

                petLiveData.value = dataPetReferences

            }

            override fun onCancelled(p0: DatabaseError) {
                throw Throwable("$p0")
            }
        })
    }

    fun getAllDataTips(){
        val database = FirebaseDatabase.getInstance().reference
        val dataTipsReferences = ArrayList<ReferencesTipsModel>()

        database.child(REF_NAME_TIPS).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if (dataTipsReferences.size>0)
                    dataTipsReferences.clear()

                for (i in p0.children){
                    val referencesTipsModel:ReferencesTipsModel? = i.getValue(ReferencesTipsModel::class.java)

                    referencesTipsModel?.let {
                        it.id = i.key
                        dataTipsReferences.add(it)
                    }
                }
                tipsLiveData.value = dataTipsReferences
            }

            override fun onCancelled(p0: DatabaseError) {
                throw Throwable("$p0")
            }
        })
    }

}


