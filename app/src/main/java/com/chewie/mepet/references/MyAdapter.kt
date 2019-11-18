package com.chewie.mepet.data.references

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MyAdapter(private val myContext:Context?,fm: FragmentManager?, internal var totalTabs:Int):FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        when(p0){
            0->{
                return PetListFragment()
            }
            1->{
                return TipsFragment()
            }
            else -> return null
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}