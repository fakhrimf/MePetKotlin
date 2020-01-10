package com.chewie.mepet.references

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.chewie.mepet.R
import com.chewie.mepet.model.PetDetailProfile

class PetViewHolder(inflater: LayoutInflater, parent:ViewGroup):RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pet_profile,parent,false)){
    private var txtName: TextView? = null
    private var txtType:TextView?=null

    init {
        txtName = itemView.findViewById(R.id.mainText)
        txtType = itemView.findViewById(R.id.subText)
    }

    fun bind(petProfile:PetDetailProfile){
        txtName?.text = petProfile.petName
        txtType?.text = petProfile.petType
    }
}