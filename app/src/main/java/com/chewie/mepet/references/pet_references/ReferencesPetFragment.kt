package com.chewie.mepet.references.pet_references


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chewie.mepet.R
import com.chewie.mepet.model.ReferencesPetModel
import com.chewie.mepet.references.ReferencesPetAdapter
import com.chewie.mepet.references.ReferencesVM
import com.chewie.mepet.utils.PET_INTENT_KEY
import kotlinx.android.synthetic.main.fragment_pet.*

class ReferencesPetFragment : Fragment(),
    ReferencesPetUserClickListener {
    private val vm: ReferencesVM by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(ReferencesVM::class.java)
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
        petSwipeRefresh.setOnRefreshListener {
            setRecycler()
        }
        setHasOptionsMenu(true)
    }

    private fun setRecycler() {
        petSwipeRefresh.isRefreshing = true
        vm.getAllDataPet()
        rvPetReferences.apply {
            layoutManager = LinearLayoutManager(requireContext())
            vm.petLiveData.value?.let {
                adapter =
                    ReferencesPetAdapter(it, this@ReferencesPetFragment)
            }
        }
        vm.petLiveData.observe(viewLifecycleOwner, Observer<ArrayList<ReferencesPetModel>> {
            rvPetReferences.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ReferencesPetAdapter(
                    it,
                    this@ReferencesPetFragment
                )
            }
            petSwipeRefresh.isRefreshing = false
        })

    }

    override fun onClick(model: ReferencesPetModel) {
        val intent = Intent(requireContext(), ReferencesPetDetailActivity::class.java)
        intent.putExtra(PET_INTENT_KEY, model)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.referenceswithsearch, menu)
        val menuItem = menu.findItem(R.id.search_view)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("####", "$query")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

}
