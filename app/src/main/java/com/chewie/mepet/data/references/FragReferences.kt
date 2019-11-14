package com.chewie.mepet.data.references


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chewie.mepet.R

/**
 * A simple [Fragment] subclass.
 */
class FragReferences : Fragment() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var icon:Drawable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_frag_references, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        viewPager = view.findViewById<ViewPager>(R.id.viewPager)


        tabLayout!!.addTab(tabLayout!!.newTab().setIcon(R.drawable.pawprint))
        tabLayout!!.addTab(tabLayout!!.newTab().setIcon(R.drawable.icons_light))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL


        val adapter = MyAdapter(
            context,
            fragmentManager,
            tabLayout!!.tabCount
        )
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }
}
