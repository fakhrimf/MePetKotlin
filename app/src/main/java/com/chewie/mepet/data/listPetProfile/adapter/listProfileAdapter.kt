package com.chewie.mepet.data.listPetProfile.adapter

import android.app.Activity
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.chewie.mepet.model.pet_detail_profile

class listProfileAdapter(private val petList: List<pet_detail_profile>):RecyclerView.Adapter<PetViewHolder>() {

    var sharPref:SharedPreferences?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PetViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = petList.size

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet:pet_detail_profile = petList[position]
        holder.bind(pet)

        holder.itemView.setOnClickListener {
            sharPref = holder.itemView.context.getSharedPreferences("pref",0);

            val editor = sharPref!!.edit()
            editor.putInt("id",petList.get(position).id_pet)
            editor.apply()

            Log.d("idnya",sharPref!!.getInt("id",0).toString())
        }
    }


}