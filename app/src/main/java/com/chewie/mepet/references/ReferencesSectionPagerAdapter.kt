package com.chewie.mepet.references

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ReferencesSectionPagerAdapter(fm: FragmentManager?, private var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        return when (p0) {
            0 -> {
                ReferencesPetFragment()
            }
            1 -> {
                ReferencesTipsFragment()
            }
            else -> null
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}