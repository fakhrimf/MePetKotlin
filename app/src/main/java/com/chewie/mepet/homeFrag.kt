package com.chewie.mepet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*

class homeFrag : Fragment() {
    companion object {
        fun newInstance(): homeFrag {
            return homeFrag()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        layoutFood.setOnClickListener {
            val sf = fragmentManager?.beginTransaction()
            sf?.setCustomAnimations(R.anim.enter, R.anim.exit)?.replace(R.id.fragment, shop())
                ?.commit()
            sf?.addToBackStack(null)
            activity?.fab1?.hide()
            activity?.nav_view?.setCheckedItem(R.id.nav_meshop)
        }
        layoutNextReminder.setOnClickListener {
            val sf = fragmentManager?.beginTransaction()
            sf?.setCustomAnimations(R.anim.enter, R.anim.exit)?.replace(R.id.fragment, reminderFrag())
                ?.commit()
            sf?.addToBackStack(null)
            activity?.fab1?.hide()
            activity?.nav_view?.setCheckedItem(R.id.nav_reminder)
        }
        cardNextReminder.setOnClickListener {

        }
        cardFood.setOnClickListener {

        }
    }
}
