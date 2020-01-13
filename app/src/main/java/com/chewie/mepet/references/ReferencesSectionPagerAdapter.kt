package com.chewie.mepet.references

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

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