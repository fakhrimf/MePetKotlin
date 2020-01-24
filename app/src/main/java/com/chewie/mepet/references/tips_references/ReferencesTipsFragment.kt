package com.chewie.mepet.references.tips_references


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chewie.mepet.R
import com.chewie.mepet.model.ReferencesTipsModel
import com.chewie.mepet.references.ReferencesTipsAdapter
import com.chewie.mepet.references.ReferencesVM
import com.chewie.mepet.utils.TIPS_INTENT_KEY
import kotlinx.android.synthetic.main.fragment_tips.*

class ReferencesTipsFragment : Fragment(),
    ReferencesTipsUserClickListener {
    private val vm: ReferencesVM by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(
            ReferencesVM::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecycler()
        tipsSwipeRefresh.setOnRefreshListener {
            setRecycler()
        }
    }

    private fun setRecycler() {
        tipsSwipeRefresh.isRefreshing = true
        vm.getAllDataTips()
        rvTipsReferences.apply {
            vm.tipsLiveData.value?.let {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ReferencesTipsAdapter(
                    it,
                    this@ReferencesTipsFragment
                )
            }
        }
        vm.tipsLiveData.observe(viewLifecycleOwner,Observer<ArrayList<ReferencesTipsModel>>{
            rvTipsReferences.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ReferencesTipsAdapter(
                    it,
                    this@ReferencesTipsFragment
                )
            }
            tipsSwipeRefresh.isRefreshing = false
        })
    }

    override fun onClick(model: ReferencesTipsModel) {
        val intent = Intent(requireContext(),ReferencesTipsDetailActivity::class.java)
        intent.putExtra(TIPS_INTENT_KEY,model)
        startActivity(intent)
    }
}
