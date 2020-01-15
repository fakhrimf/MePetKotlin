package com.chewie.mepet.references

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.util.Log
import com.chewie.mepet.model.ReferencesPetModel
import com.chewie.mepet.model.ReferencesTipsModel
import com.chewie.mepet.utils.REF_NAME
import com.google.firebase.database.*

class ReferencesVM(application: Application) : AndroidViewModel(application) {
    fun getAllData(){
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference(REF_NAME)

        ref.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.d("FIREBASE_UTILS","onCancelled")
            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.d("FIREBASE_UTILS","onDataChanged")

            }

        })
    }

    fun referencesPetData() : ArrayList<ReferencesPetModel> {
        val list = ArrayList<ReferencesPetModel>()
        for(i in 0 until 10) {
            list.add(ReferencesPetModel(i,"","Test $i",""))
        }
        return list
    }

    fun referencesTipsData() : ArrayList<ReferencesTipsModel> {
        val list = ArrayList<ReferencesTipsModel>()
        for(i in 0 until 10) {
            list.add(ReferencesTipsModel(i,"","Test $i",""))
        }
        return list
    }
}