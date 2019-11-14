package com.chewie.mepet.data.listPetProfile


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.data.listPetProfile.adapter.listProfileAdapter
import kotlinx.android.synthetic.main.fragment_list_profile.*

/**
 * A simple [Fragment] subclass.
 */
class listProfileFragment : Fragment() {

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
        var petList = db.getAllProfile()

        val petAdapter = listProfileAdapter(petList)

        listView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = petAdapter
        }
    }
}
