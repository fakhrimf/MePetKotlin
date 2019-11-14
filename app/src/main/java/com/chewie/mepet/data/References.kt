package com.chewie.mepet.data

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.R
import kotlinx.android.synthetic.main.activity_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
        nav_view.getMenu().getItem(1).setChecked(true)
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



