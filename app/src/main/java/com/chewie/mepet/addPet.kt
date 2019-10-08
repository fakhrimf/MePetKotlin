package com.chewie.mepet

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.pojo.pet_detail_profile
import kotlinx.android.synthetic.main.fragment_add_pet.*
import kotlinx.android.synthetic.main.fragment_home.*

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

    fun setNpValue() {
        npBeratBadanUtama.setMinValue(0)
        npBeratBadanSekunder.setMinValue(1)
        npBeratBadanSekunder.setMaxValue(9)
        npBeratBadanUtama.setMaxValue(18)
    }

    fun initialization(){
        petName = et_petname.toString()
        petAge = et_age.toString()
        petType = cbx_pettype.selectedItem.toString()
        firstWeight = npBeratBadanUtama.value.toString()
        secondWeight = npBeratBadanSekunder.value.toString()
        petWeight = (firstWeight+"."+secondWeight).toFloat()
    }

    fun insertData(){
        val db = MepetDatabaseHelper(context)
        var success:Boolean = false;
        val pet:pet_detail_profile = pet_detail_profile();
        pet.pet_name = this.petName
        pet.pet_type = this.petType
        pet.pet_age = this.petAge
        pet.pet_weight = this.petWeight
        
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setNpValue()
        initialization()
    }
}

