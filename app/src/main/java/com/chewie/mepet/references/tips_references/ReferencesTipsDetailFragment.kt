package com.chewie.mepet.references.tips_references


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.chewie.mepet.BR
import com.chewie.mepet.R
import com.chewie.mepet.databinding.FragmentDetailTipsBinding
import com.chewie.mepet.references.ReferencesVM
import com.chewie.mepet.utils.TIPS_INTENT_KEY
import kotlinx.android.synthetic.main.content_home.*

/**
 * A simple [Fragment] subclass.
 */
class ReferencesTipsDetailFragment : Fragment() {

    private val vm by lazy{
        ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(activity!!.application)).get(ReferencesVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailTipsBinding.inflate(inflater,container,false)
        binding.apply {
            setVariable(BR.model,arguments?.getParcelable(TIPS_INTENT_KEY))
            executePendingBindings()
        }
        return if (arguments == null){
            inflater.inflate(R.layout.fragment_detail_tips,container,false)
        }else{
            binding.root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentManager?.popBackStack()
    }
}
