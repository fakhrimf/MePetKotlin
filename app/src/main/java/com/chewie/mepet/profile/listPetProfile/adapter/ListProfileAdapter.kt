package com.chewie.mepet.profile.listPetProfile.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chewie.mepet.BR
import com.chewie.mepet.databinding.ItemPetProfileBinding
import com.chewie.mepet.model.PetDetailProfile
import com.chewie.mepet.profile.ProfileClickListener

class ListProfileAdapter(private val petList: List<PetDetailProfile>, private val listener: ProfileClickListener) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ListProfileAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPetProfileBinding.inflate(inflater, parent, false)
        return PetViewHolder(binding.apply {
            listener = this@ListProfileAdapter.listener
        })
    }

    override fun getItemCount(): Int = petList.size

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(petList[position])
    }

    inner class PetViewHolder(private val binding: ItemPetProfileBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PetDetailProfile) {
            binding.setVariable(BR.model, item)
            binding.executePendingBindings()
        }
    }


}