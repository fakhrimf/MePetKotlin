package com.chewie.mepet.references

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chewie.mepet.BR
import com.chewie.mepet.databinding.ItemPetTipsBinding
import com.chewie.mepet.model.ReferencesTipsModel

class ReferencesTipsAdapter(private val referencesTipsList: ArrayList<ReferencesTipsModel>, private val listener: ReferencesTipsUserClickListener) : RecyclerView.Adapter<ReferencesTipsAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPetTipsBinding.inflate(inflater, parent, false)
        return Holder(binding.apply {
            listener = this@ReferencesTipsAdapter.listener
        })
    }

    override fun getItemCount(): Int = referencesTipsList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(referencesTipsList[position])
    }

    inner class Holder(private val binding: ItemPetTipsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReferencesTipsModel) {
            binding.setVariable(BR.model, item)
            binding.executePendingBindings()
        }
    }
}