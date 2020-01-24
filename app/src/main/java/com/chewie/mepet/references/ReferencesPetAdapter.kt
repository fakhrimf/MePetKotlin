package com.chewie.mepet.references

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chewie.mepet.BR
import com.chewie.mepet.databinding.ItemPetReferencesBinding
import com.chewie.mepet.model.ReferencesPetModel
import com.chewie.mepet.references.pet_references.ReferencesPetUserClickListener

class ReferencesPetAdapter(private val referencesPetList: ArrayList<ReferencesPetModel>, private val listener: ReferencesPetUserClickListener) : RecyclerView.Adapter<ReferencesPetAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPetReferencesBinding.inflate(inflater, parent, false)
        return Holder(binding.apply {
            listener = this@ReferencesPetAdapter.listener
        })
    }

    override fun getItemCount(): Int = referencesPetList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(referencesPetList[position])
    }

    inner class Holder(private val binding: ItemPetReferencesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReferencesPetModel) {
            binding.setVariable(BR.model, item)
            binding.executePendingBindings()
        }
    }
}