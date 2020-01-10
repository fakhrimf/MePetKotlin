package com.chewie.mepet.profile.listPetProfile


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.home.HomeFragment
import com.chewie.mepet.model.PetDetailProfile
import com.chewie.mepet.profile.ProfileClickListener
import com.chewie.mepet.profile.listPetProfile.adapter.ListProfileAdapter
import com.chewie.mepet.utils.AlarmReceiver
import com.chewie.mepet.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_list_profile.*

class ListProfileFragment : Fragment(), ProfileClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_profile, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val db = MepetDatabaseHelper(context)
        val petList = db.getAllProfile()

        val petAdapter = ListProfileAdapter(petList,this)

        listView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = petAdapter
        }
    }

    private fun toFragment(fragment: Fragment, title: String, item: Int) {
        val handler = Handler()
        val sf = fragmentManager?.beginTransaction()
        sf?.setCustomAnimations(R.anim.enter, R.anim.exit)
            ?.replace(R.id.fragment, fragment)?.commit()
        sf?.addToBackStack(null)
        handler.postDelayed({
            activity?.invalidateOptionsMenu()
        }, 50)
        activity?.tvMepet?.text = title
        activity?.nav_view?.setCheckedItem(item)
    }

    override fun onClick(pet: PetDetailProfile) {
        val sharPref = SharedPreference(requireContext())
        val db = MepetDatabaseHelper(context)
        val profile = db.getReminder(pet.idPet)

        val alarmReceiver = AlarmReceiver()

        sharPref.setId(pet.idPet?:0)

        if (profile.jamPagi.contains(":")) {
            sharPref.setJamPagi(profile.jamPagi)
            alarmReceiver.scheduleNotifPagi(requireContext())
        } else if (profile.jamPagi == "" || profile.jamPagi == null) {
            sharPref.setJamPagi("")
            alarmReceiver.scheduleNotifPagi(requireContext())
        }

        if (profile.jamPagi.contains(":")) {
            sharPref.setJamSiang(profile.jamPagi)
            alarmReceiver.scheduleNotifSiang(requireContext())
        } else if (profile.jamPagi == "" || profile.jamPagi == null) {
            sharPref.setJamSiang("")
            alarmReceiver.scheduleNotifSiang(requireContext())
        }
        toFragment(HomeFragment(),getString(R.string.menu_home),R.id.nav_home)

        Log.d("idnya", sharPref.getId().toString())
    }

}
