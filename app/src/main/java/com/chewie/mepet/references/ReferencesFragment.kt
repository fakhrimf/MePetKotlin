package com.chewie.mepet.references


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.chewie.mepet.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_frag_references.*

class ReferencesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_frag_references, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val drawablePaw = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_pawprint)
        val drawableLight = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_tips)
        tabLayout?.let {
            it.addTab(it.newTab().setIcon(drawablePaw))
            it.addTab(it.newTab().setIcon(drawableLight))
            it.tabGravity = TabLayout.GRAVITY_FILL
        }

        var adapter: ReferencesSectionPagerAdapter? = null
        tabLayout?.let {
            adapter = ReferencesSectionPagerAdapter(childFragmentManager, it.tabCount)
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
