package com.chewie.mepet.viewmodel

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.pet_detail_profile
import com.chewie.mepet.model.pet_profile
import com.chewie.mepet.view.homeFrag
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_add_pet.*
import kotlinx.android.synthetic.main.fragment_home.*

//ViewModel for addPet
class addPetVM(
    private var context: Context?,
    private var activity: Activity?,
    private var fragmentManager: FragmentManager?
) {
    private val addPet = com.chewie.mepet.view.addPet()
    private var petName: String = ""
    private var petAge: String = ""
    private var petType: String = ""
    private var firstWeight: String = ""
    private var secondWeight: String = ""
    private var petWeight: Float = 0.0F

    fun setNpValue() {
        addPet.npBeratBadanUtama.minValue = 0
        addPet.npBeratBadanSekunder.minValue = 1
        addPet.npBeratBadanSekunder.maxValue = 9
        addPet.npBeratBadanUtama.maxValue = 18
    }

    private fun initialization() {
        petName = addPet.et_petname.text.toString()
        petAge = addPet.et_age.text.toString()
        petType = addPet.cbx_pettype.selectedItem.toString()
        firstWeight = addPet.npBeratBadanUtama.value.toString()
        secondWeight = addPet.npBeratBadanSekunder.value.toString()
        petWeight = ("$firstWeight.$secondWeight").toFloat()
    }

    private fun cekEmpty(): Boolean {
        if (petName.isEmpty() || petAge.isEmpty()) {
            if (petName.isEmpty()) {
                addPet.et_petname.error = "Nama Harus diisi"
            }
            if (petAge.isEmpty()) {
                addPet.et_age.error = "Usia harus diisi"
            }
        } else {
            return true
        }
        return false
    }

    fun insertData() {
        initialization()
        if (cekEmpty()) {
            val db = MepetDatabaseHelper(context)
            var success = false
            val pet = pet_detail_profile()
            val petProfile = pet_profile()

            pet.pet_name = this.petName
            pet.pet_type = this.petType
            pet.pet_age = this.petAge.toInt()
            pet.pet_weight = this.petWeight


            petProfile.id_detail_profile = pet.id_pet
            db.insertPet(pet, petProfile)
//            Toast.makeText(context, "Berhasil! " + pet.id_pet, Toast.LENGTH_LONG).show()
            Snackbar.make(
                activity!!.findViewById(android.R.id.content), "Data berhasil Masuk!",
                Snackbar.LENGTH_SHORT
            ).show()
            toFragment(homeFrag(), "Home", R.id.nav_home)
            println(petName)
        }
    }

    private fun toFragment(fragment: Fragment, title: String, item: Int) {
        val handler = Handler()
        val delay: Long = 50
        val sf = fragmentManager?.beginTransaction()
        sf?.setCustomAnimations(R.anim.enter, R.anim.exit)
            ?.replace(R.id.fragment, fragment)
            ?.commit()
        sf?.addToBackStack(null)
        activity?.fab1?.hide()
        activity?.tvMepet?.text = title
        handler.postDelayed({
            activity?.invalidateOptionsMenu()
        }, delay)
        activity?.nav_view?.setCheckedItem(item)
    }

    private fun getCbxIndex(type: String): Int {
        for (i in 0 until addPet.cbx_pettype.count) {
            if (addPet.cbx_pettype.getItemAtPosition(i) == type) {
                return i
            }
        }
        return 0
    }

    fun editSet(arguments: Bundle?) {
        val dbManager = MepetDatabaseHelper(context)
        val args = arguments
        val id = args?.getInt("id")
        if (id != null) {
            val detailProfile = dbManager.getPetById(id)
            addPet.et_petname.setText(detailProfile.pet_name)
            addPet.et_age.setText(detailProfile.pet_age.toString())
//            Toast.makeText(context,"jenis = "+detailProfile.pet_type,Toast.LENGTH_LONG).show()
            addPet.cbx_pettype.setSelection(getCbxIndex(detailProfile.pet_type))
            val beratFirst = detailProfile.pet_weight.toString().split(".")[0].toInt()
            val beratKedua = detailProfile.pet_weight.toString().split(".")[1].toInt()
            addPet.npBeratBadanUtama.value = beratFirst
            addPet.npBeratBadanSekunder.value = beratKedua
            addPet.btnAddPet.text = "Update"
        }
    }
}