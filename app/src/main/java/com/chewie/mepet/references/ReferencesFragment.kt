package com.chewie.mepet.references


import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.R
import com.chewie.mepet.home.HomeVM

class ReferencesFragment : Fragment() {
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private val vm: ReferencesVM by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(ReferencesVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_frag_references, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tabLayout = view?.findViewById(R.id.tabLayout)
        viewPager = view?.findViewById(R.id.viewPager)

        tabLayout?.let {
            it.addTab(it.newTab().setIcon(R.drawable.pawprint))
            it.addTab(it.newTab().setIcon(R.drawable.icons_light))
            it.tabGravity = TabLayout.GRAVITY_FILL
        }

        var adapter: ReferencesSectionPagerAdapter? = null
        tabLayout?.let {
            adapter = ReferencesSectionPagerAdapter(
                childFragmentManager,
                it.tabCount
            )
        }

        viewPager?.let {
            it.adapter = adapter

            it.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
            tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    it.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }
    }
}
