package com.chewie.mepet.references


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chewie.mepet.R
import com.chewie.mepet.model.ReferencesPetModel
import kotlinx.android.synthetic.main.fragment_pet.*

class ReferencesPetFragment : Fragment(), ReferencesPetUserClickListener {
    private val vm: ReferencesVM by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(ReferencesVM::class.java)
    }

    private val adapter by lazy {
        ReferencesPetAdapter(vm.getAllDataPet(), this)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecycler()
    }

    private fun setRecycler() {
        vm.getLiveDataPet().observe(viewLifecycleOwner,Observer<ArrayList<ReferencesPetModel>> { t ->
            Log.d("SIZE","${t.size}")
            if (t!=null){
                rvPetReferences.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = ReferencesPetAdapter(t,this@ReferencesPetFragment)
                }
            }
        })

    }

    private fun observer(){

    }



    override fun onClick(model: ReferencesPetModel) {
        Toast.makeText(requireContext(), model.title, Toast.LENGTH_LONG).show()
    }


}
