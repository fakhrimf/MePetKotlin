package com.chewie.mepet.home

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.pet_detail_profile
import com.chewie.mepet.model.pet_profile
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_add_pet.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddPetFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddPetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddPetFragment : Fragment() {

    private var AddPetVM: AddPetVM? = null

    companion object {
        fun newInstance(): AddPetFragment {
            return AddPetFragment()
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
        npBeratBadanUtama.minValue = 0
        npBeratBadanSekunder.minValue = 1
        npBeratBadanSekunder.maxValue = 9
        npBeratBadanUtama.maxValue = 18
    }

    private fun getCbxIndex(type: String): Int {
        for (i in 0 until cbx_pettype.count) {
            if (cbx_pettype.getItemAtPosition(i) == type) {
                return i
            }
        }
        return 0
    }
    private var petName: String = ""
    private var petAge: String = ""
    private var petType: String = ""
    private var firstWeight: String = ""
    private var secondWeight: String = ""
    private var petWeight: Float = 0.0F


    private fun initialization() {
        petName = activity?.et_petname?.text.toString()
        petAge = activity?.et_age?.text.toString()
        petType = activity?.cbx_pettype?.selectedItem.toString()
        firstWeight = activity?.npBeratBadanUtama?.value.toString()
        secondWeight = activity?.npBeratBadanSekunder?.value.toString()
        petWeight = ("$firstWeight.$secondWeight").toFloat()
    }

    private fun cekEmpty(): Boolean {
        if (petName.isEmpty() || petAge.isEmpty()) {
            if (petName.isEmpty()) {
                activity?.et_petname?.error = "Nama Harus diisi"
            }
            if (petAge.isEmpty()) {
                activity?.et_age?.error = "Usia harus diisi"
            }
        } else {
            return true
        }
        return false
    }

    private fun insertData() {
        println("insert data")
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
//            Toast.makeText(context, "Berhasil! " + PetFragment.id_pet, Toast.LENGTH_LONG).show()
            Snackbar.make(
                activity!!.findViewById(android.R.id.content), "Data berhasil Masuk!",
                Snackbar.LENGTH_SHORT
            ).show()
            toFragment(HomeFragment(), "Home", R.id.nav_home)
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

    private fun editSet(arguments: Bundle?) {
        val dbManager = MepetDatabaseHelper(context)
        val args = arguments
        val id = args?.getInt("id")
        if (id != null) {
            val detailProfile = dbManager.getPetById(id)
            activity?.et_petname?.setText(detailProfile.pet_name)
            activity?.et_age?.setText(detailProfile.pet_age.toString())
//            Toast.makeText(context,"jenis = "+detailProfile.pet_type,Toast.LENGTH_LONG).show()
            activity?.cbx_pettype?.setSelection(getCbxIndex(detailProfile.pet_type))
            val beratFirst = detailProfile.pet_weight.toString().split(".")[0].toInt()
            val beratKedua = detailProfile.pet_weight.toString().split(".")[1].toInt()
            activity?.npBeratBadanUtama?.value = beratFirst
            activity?.npBeratBadanSekunder?.value = beratKedua
            activity?.btnAddPet?.text = "Update"
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setNpValue()
        AddPetVM = AddPetVM(context, activity, fragmentManager)
        editSet(arguments)
        btnAddPet.setOnClickListener{
            if(activity?.tvMepet?.text == "Edit Pet"){
//                UpdatePet()
            } else {
                initialization()
                insertData()
            }
        }
    }
}

