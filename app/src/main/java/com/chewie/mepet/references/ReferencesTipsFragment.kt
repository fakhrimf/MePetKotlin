package com.chewie.mepet.references


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chewie.mepet.R
import com.chewie.mepet.model.ReferencesTipsModel
import kotlinx.android.synthetic.main.fragment_tips.*

class ReferencesTipsFragment : Fragment(), ReferencesTipsUserClickListener {
    private val vm: ReferencesVM by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(ReferencesVM::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecycler()
    }

    private fun setRecycler() {
        vm.getAllDataTips()
        rvTipsReferences.apply {
            vm.tipsLiveData.value?.let {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ReferencesTipsAdapter(it,this@ReferencesTipsFragment)
            }
        }
        vm.tipsLiveData.observe(viewLifecycleOwner,Observer<ArrayList<ReferencesTipsModel>>{
            rvTipsReferences.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ReferencesTipsAdapter(it,this@ReferencesTipsFragment)
            }
        })
    }

    override fun onClick(model: ReferencesTipsModel) {
        Toast.makeText(requireContext(), model.title, Toast.LENGTH_LONG).show()
    }
}
