package com.chewie.mepet.listPetProfile.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.chewie.mepet.R
import com.chewie.mepet.pojo.pet_detail_profile

class PetViewHolder(inflater: LayoutInflater, parent:ViewGroup):RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pet_profile,parent,false)){
    private var txtName: TextView? = null
    private var txtType:TextView?=null

    init {
        txtName = itemView.findViewById(R.id.maintext)
        txtType = itemView.findViewById(R.id.subtext)
    }

    fun bind(petProfile:pet_detail_profile){
        txtName?.text = petProfile.pet_name
        txtType?.text = petProfile.pet_type
    }
}