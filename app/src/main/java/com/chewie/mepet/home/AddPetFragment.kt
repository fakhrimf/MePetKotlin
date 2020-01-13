package com.chewie.mepet.home

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.utils.ARGUMENTS_ID_KEY
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_add_pet.*
import kotlinx.android.synthetic.main.fragment_home.*

class AddPetFragment : Fragment() {
    private val vm by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(activity!!.application)).get(AddPetVM::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_pet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setNpValue()
        editSet(arguments)
        btnAddPet.setOnClickListener {
            if (checkEmpty() && tvMepet?.text != getString(R.string.edit_pet_data)) {
                val firstWeight = npBeratBadanUtama?.value.toString()
                val secondWeight = npBeratBadanSekunder?.value.toString()
                vm.insertData(et_petname?.text.toString(), cbx_pettype?.selectedItem.toString(), et_age?.text.toString().toInt(), ("$firstWeight.$secondWeight").toFloat())
                toFragment(HomeFragment(), getString(R.string.home), R.id.nav_home)
            }
        }
    }

    private fun toFragment(fragment: Fragment, title: String, item: Int) {
        val sf = fragmentManager?.beginTransaction()
        sf?.setCustomAnimations(R.anim.enter, R.anim.exit)?.replace(R.id.fragment, fragment)?.commit()
        sf?.addToBackStack(null)
        fab1?.hide()
        activity?.tvMepet?.text = title
        activity?.nav_view?.setCheckedItem(item)
    }

    private fun editSet(arguments: Bundle?) {
        val dbManager = MepetDatabaseHelper(context)
        val id = arguments?.getInt(ARGUMENTS_ID_KEY)
        if (id != null) {
            val detailProfile = dbManager.getPetById(id)
            detailProfile?.let {
                et_petname?.setText(it.petName)
                et_age?.setText(it.petAge.toString())
                cbx_pettype?.setSelection(getCbxIndex(it.petType))
                val beratFirst = it.petWeight.toString().split(".")[0].toInt()
                val beratKedua = it.petWeight.toString().split(".")[1].toInt()
                npBeratBadanUtama?.value = beratFirst
                npBeratBadanSekunder?.value = beratKedua
            }
            btnAddPet?.text = getString(R.string.update)
        }
    }

    private fun setNpValue() {
        npBeratBadanUtama.minValue = 1
        npBeratBadanUtama.maxValue = 18
        npBeratBadanSekunder.minValue = 0
        npBeratBadanSekunder.maxValue = 9
    }

    private fun getCbxIndex(type: String): Int {
        for (i in 0 until cbx_pettype.count) {
            if (cbx_pettype.getItemAtPosition(i) == type) {
                return i
            }
        }
        return 0
    }

    private fun checkEmpty(): Boolean {
        if (et_petname.text?.isEmpty() == true) {
            et_petname.error = getString(R.string.warning_name)
        }
        if (et_age.text?.isEmpty() == true) {
            et_age.error = getString(R.string.warning_age)
        } else {
            return true
        }
        return false
    }
}

