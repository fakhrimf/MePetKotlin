package com.chewie.mepet.listPetProfile.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chewie.mepet.pojo.pet_detail_profile

class listProfileAdapter(private val petList: List<pet_detail_profile>):RecyclerView.Adapter<PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PetViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int = petList.size

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet:pet_detail_profile = petList[position]
        holder.bind(pet)
    }


}