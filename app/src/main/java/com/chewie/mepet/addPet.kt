package com.chewie.mepet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.pojo.pet_detail_profile
import com.chewie.mepet.pojo.pet_profile
import kotlinx.android.synthetic.main.fragment_add_pet.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [addPet.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [addPet.newInstance] factory method to
 * create an instance of this fragment.
 */
class addPet : Fragment() {

    var petName:String=""
    var petAge:String=""
    var petType:String=""
    var firstWeight:String=""
    var secondWeight:String=""
    var petWeight:Float= 0.0F

    companion object {
        fun newInstance(): addPet {
            return addPet()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_pet, container, false)
    }

    private fun setNpValue() {
        npBeratBadanUtama.setMinValue(0)
        npBeratBadanSekunder.setMinValue(1)
        npBeratBadanSekunder.setMaxValue(9)
        npBeratBadanUtama.setMaxValue(18)
    }

    private fun initialization(){
        petName = et_petname.text.toString()
        petAge = et_age.text.toString()
        petType = cbx_pettype.selectedItem.toString()
        firstWeight = npBeratBadanUtama.value.toString()
        secondWeight = npBeratBadanSekunder.value.toString()
        petWeight = ("$firstWeight.$secondWeight").toFloat()
    }

    private fun cekEmpty():Boolean {
        if(petName.isEmpty() || petAge.isEmpty()) {
            if (petName.isEmpty()) {
                et_petname.error = "Nama Harus diisi"
            }
            if (petAge.isEmpty()) {
                et_age.error = "Usia harus diisi"
            }
        } else {
            return true
        }
        return false
    }

    private fun insertData(){
        initialization()
        if(cekEmpty()) {
            val db = MepetDatabaseHelper(context)
            var success: Boolean = false
            val pet: pet_detail_profile = pet_detail_profile()
            val petProfile = pet_profile()

            pet.pet_name = this.petName
            pet.pet_type = this.petType
            pet.pet_age = this.petAge.toInt()
            pet.pet_weight = this.petWeight


            petProfile.id_detail_profile = pet.id_pet
            db.insertPet(pet, petProfile)
            Toast.makeText(context, "Berhasil! " + pet.id_pet, Toast.LENGTH_LONG).show()
            println(petName)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setNpValue()
        btnAddPet.setOnClickListener{
            initialization()
            insertData()
        }
    }
}

