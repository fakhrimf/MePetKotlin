package com.chewie.mepet.references

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.chewie.mepet.references.pet_references.ReferencesPetFragment
import com.chewie.mepet.references.tips_references.ReferencesTipsFragment

class ReferencesSectionPagerAdapter(fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> ReferencesPetFragment()
            else -> ReferencesTipsFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}