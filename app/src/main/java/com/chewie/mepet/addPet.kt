package com.chewie.mepet

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    fun setNpValue() {
        npBeratBadanUtama.setMinValue(0)
        npBeratBadanSekunder.setMinValue(1)
        npBeratBadanSekunder.setMaxValue(9)
        npBeratBadanUtama.setMaxValue(18)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setNpValue()
    }
}

