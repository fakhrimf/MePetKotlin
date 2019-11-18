package com.chewie.mepet.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_add_pet.*
import kotlinx.android.synthetic.main.fragment_home.*

class AddPetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_pet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm = AddPetVM(context)
        vm.setNpValue(npBeratBadanUtama, npBeratBadanSekunder)
        editSet(arguments)
        btnAddPet.setOnClickListener {
            if (tvMepet?.text == "Edit Pet") {
//                UpdatePet()
            } else {
                if (vm.checkEmpty(et_petname, et_age)) {
                    val firstWeight = npBeratBadanUtama?.value.toString()
                    val secondWeight = npBeratBadanSekunder?.value.toString()
                    vm.insertData(et_petname?.text.toString(), cbx_pettype?.selectedItem.toString(), et_age?.text.toString().toInt(), ("$firstWeight.$secondWeight").toFloat())
                    toFragment(HomeFragment(), "Home", R.id.nav_home)
                }
            }
        }
    }

    private fun toFragment(fragment: Fragment, title: String, item: Int) {
        val sf = fragmentManager?.beginTransaction()
        sf?.setCustomAnimations(R.anim.enter, R.anim.exit)?.replace(R.id.fragment, fragment)
            ?.commit()
        sf?.addToBackStack(null)
        fab1?.hide()
        activity?.tvMepet?.text = title
        activity?.nav_view?.setCheckedItem(item)
    }

    private fun editSet(arguments: Bundle?) {
        val dbManager = MepetDatabaseHelper(context)
        val vm = AddPetVM(context)
        val id = arguments?.getInt("id")
        if (id != null) {
            val detailProfile = dbManager.getPetById(id)
            et_petname?.setText(detailProfile.pet_name)
            et_age?.setText(detailProfile.pet_age.toString())
            cbx_pettype?.setSelection(vm.getCbxIndex(cbx_pettype, detailProfile.pet_type))
            val beratFirst = detailProfile.pet_weight.toString().split(".")[0].toInt()
            val beratKedua = detailProfile.pet_weight.toString().split(".")[1].toInt()
            npBeratBadanUtama?.value = beratFirst
            npBeratBadanSekunder?.value = beratKedua
            btnAddPet?.text = "Update"
        }
    }
}

