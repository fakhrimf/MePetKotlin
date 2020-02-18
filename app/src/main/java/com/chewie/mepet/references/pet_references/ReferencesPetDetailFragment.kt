package com.chewie.mepet.references.pet_references


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import com.chewie.mepet.R
import com.chewie.mepet.databinding.FragmentDetailCatBinding
import com.chewie.mepet.utils.PET_INTENT_KEY

class ReferencesPetDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailCatBinding.inflate(inflater, container, false)
        binding.apply {
            setVariable(BR.model, arguments?.getParcelable(PET_INTENT_KEY))
            executePendingBindings()
        }
        return if (arguments == null) {
            inflater.inflate(R.layout.fragment_detail_cat, container, false)
        } else {
            binding.root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        @Suppress("DEPRECATION")
        fragmentManager?.popBackStack()
    }


}
