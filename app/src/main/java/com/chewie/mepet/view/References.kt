package com.chewie.mepet.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [References.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [References.newInstance] factory method to
 * create an instance of this fragment.
 */
class References : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        fun newInstance(): References {
            return References()
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.fragment_references, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        initBtn()
    }
//    private fun initBtn(){
//        val toShop: Button = findViewById(R.id.btnToShop)
//        toShop.setOnClickListener{
//            val sf = supportFragmentManager.beginTransaction()
//            sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, shop()).commit()
//            sf.addToBackStack(null)
//            fab1.hide()
//            nav_view.getMenu().getItem(4).setChecked(true)
//        }
//    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_pet -> {
                val sf = activity!!.supportFragmentManager.beginTransaction();
                sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(
                    R.id.fragment,
                    pet()
                ).commit()
                sf.addToBackStack(null)

            }
        }
        return true
    }
}



