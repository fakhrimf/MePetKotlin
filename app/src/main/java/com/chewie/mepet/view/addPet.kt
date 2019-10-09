package com.chewie.mepet.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.R
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_add_pet.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [addPet.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [addPet.newInstance] factory method to
 * create an instance of this fragment.
 */
class addPet : Fragment() {

    private val addPetVM = com.chewie.mepet.viewmodel.addPetVM(context,activity,fragmentManager)

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addPetVM.setNpValue()
        addPetVM.editSet(arguments)
        btnAddPet.setOnClickListener{
            if(activity?.tvMepet?.text == "Edit Pet"){
//                UpdatePet()
            } else {
                addPetVM.insertData()
            }
        }
    }
}

