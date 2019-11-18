package com.chewie.mepet.references

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MyAdapter(fm: FragmentManager?, private var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        return when (p0) {
            0 -> {
                PetListFragment()
            }
            1 -> {
                TipsFragment()
            }
            else -> null
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}