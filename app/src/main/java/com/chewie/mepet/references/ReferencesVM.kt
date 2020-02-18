package com.chewie.mepet.references

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.chewie.mepet.model.ReferencesPetModel
import com.chewie.mepet.model.ReferencesTipsModel
import com.chewie.mepet.utils.REF_NAME_PET
import com.chewie.mepet.utils.REF_NAME_TIPS
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReferencesVM(application: Application) : AndroidViewModel(application) {
    val petLiveData: MutableLiveData<ArrayList<ReferencesPetModel>> by lazy {
        MutableLiveData<ArrayList<ReferencesPetModel>>()
    }

    val tipsLiveData: MutableLiveData<ArrayList<ReferencesTipsModel>> by lazy {
        MutableLiveData<ArrayList<ReferencesTipsModel>>()
    }

    private val database by lazy {
        FirebaseDatabase.getInstance().reference
    }

    fun getAllDataPet() {
        val dataPetReferences = ArrayList<ReferencesPetModel>()

        database.child(REF_NAME_PET).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

                if (dataPetReferences.size > 0) dataPetReferences.clear()

                for (i in p0.children) {
                    val petReferencesModel: ReferencesPetModel? = i.getValue(ReferencesPetModel::class.java)
                    Log.d("ADUH_SORRY", "${i.key}")
                    petReferencesModel?.let {
                        it.id = i.key
                        dataPetReferences.add(it)
                    }
                }

                petLiveData.value = dataPetReferences
            }

            override fun onCancelled(p0: DatabaseError) {
                throw Throwable("$p0")
            }
        })
    }

    fun getAllDataTips() {
        val dataTipsReferences = ArrayList<ReferencesTipsModel>()

        database.child(REF_NAME_TIPS).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (dataTipsReferences.size > 0) dataTipsReferences.clear()

                for (i in p0.children) {
                    val referencesTipsModel: ReferencesTipsModel? = i.getValue(ReferencesTipsModel::class.java)

                    referencesTipsModel?.let {
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

    fun searchReferencesTips(query: String) {
        val dataTipsReferences = ArrayList<ReferencesTipsModel>()
        if (dataTipsReferences.size > 0) dataTipsReferences.clear()
        database.child(REF_NAME_TIPS).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (i in p0.children) {
                    val model = i.getValue(ReferencesTipsModel::class.java)
                    model?.let { value ->
                        if (value.title.contains(query, ignoreCase = true)) dataTipsReferences.add(value)
                    }
                }
                tipsLiveData.value = dataTipsReferences
            }

            override fun onCancelled(p0: DatabaseError) {
                throw Throwable("$p0")
            }
        })
    }

    fun searchReferencesPet(query: String) {
        val dataPetReferences = ArrayList<ReferencesPetModel>()
        if (dataPetReferences.size > 0) dataPetReferences.clear()
        database.child(REF_NAME_PET).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (i in p0.children) {
                    val model = i.getValue(ReferencesPetModel::class.java)
                    model?.let { value ->
                        if (value.title.contains(query, ignoreCase = true)) dataPetReferences.add(value)
                    }
                }
                petLiveData.value = dataPetReferences
            }

            override fun onCancelled(p0: DatabaseError) {
                throw Throwable("$p0")
            }
        })
    }
}


