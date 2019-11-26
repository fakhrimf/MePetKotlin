package com.chewie.mepet.home

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.reminder.ReminderFragment
import com.chewie.mepet.shop.ShopFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {
    companion object {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun cekFoodAndReminder() {
        val sdfJam = SimpleDateFormat("HH", Locale.US)
        val sdfMenit = SimpleDateFormat("mm", Locale.US)
        val jamPagiDate: Date = sdfJam.parse("07")
        val menitPagiDate: Date = sdfMenit.parse("00")

        val jamSiangDate: Date = sdfJam.parse("12")
        val menitSiangDate: Date = sdfMenit.parse("00")

        val jamMalamDate: Date = sdfJam.parse("20")
        val menitMalamDate: Date = sdfMenit.parse("00")


        val jamPagi = SimpleDateFormat("HH", Locale.US).format(jamPagiDate).toInt()
        val menitPagi = SimpleDateFormat("mm", Locale.US).format(menitPagiDate).toInt()

        val jamSiang = SimpleDateFormat("HH", Locale.US).format(jamSiangDate).toInt()
        val menitSiang = SimpleDateFormat("mm", Locale.US).format(menitSiangDate).toInt()

        val jamMalam = SimpleDateFormat("HH", Locale.US).format(jamMalamDate).toInt()
        val menitMalam = SimpleDateFormat("mm", Locale.US).format(menitMalamDate).toInt()

        val calPagi = Calendar.getInstance()
        calPagi.set(Calendar.HOUR_OF_DAY, jamPagi)
        calPagi.set(Calendar.MINUTE, menitPagi)

        val calSiang = Calendar.getInstance()
        calSiang.set(Calendar.HOUR_OF_DAY, jamSiang)
        calSiang.set(Calendar.MINUTE, menitSiang)

        val calMalam = Calendar.getInstance()
        calMalam.set(Calendar.HOUR_OF_DAY, jamMalam)
        calMalam.set(Calendar.MINUTE, menitMalam)

        //Lol
        if (Calendar.getInstance().timeInMillis >= calPagi.timeInMillis) {
            cekPagi.setImageResource(R.drawable.ic_check_black_24dp)
            ivFood.setImageResource(R.drawable.ic_sun)
            val db = MepetDatabaseHelper(context)
            val profile = db.getReminder(id)

            //Todo: si Waktunya ga muncul di home, pls fix
            tvTime.text = profile.jam_siang //Next Reminder in db Todo: Implementasi Waktu dari database
            if (Calendar.getInstance().timeInMillis >= calSiang.timeInMillis) {
                cekSiang.setImageResource(R.drawable.ic_check_black_24dp)
                tvTime.text = profile.jam_malam
                ivFood.setImageResource(R.drawable.ic_night)
                if (Calendar.getInstance().timeInMillis >= calMalam.timeInMillis) {
                    cekMalam.setImageResource(R.drawable.ic_check_black_24dp)
                    tvTime.text = profile.jam_pagi
                    ivFood.setImageResource(R.drawable.ic_morning)
                }
            }
        }
    }

    private fun showData() {
        val dbManager = MepetDatabaseHelper(context)
        val detailProfile = dbManager.getPetById(id!!)

        tvNama.text = detailProfile.pet_name
        tvAge.text = detailProfile.pet_age.toString()
        tvWeight.text = detailProfile.pet_weight.toString() + " Kg"
        tvJenis.text = detailProfile.pet_type
        Log.v("Berat", detailProfile.pet_weight.toString())
    }

    //
    private fun toFragment(fragment: Fragment, title: String, item: Int) {
        val handler = Handler()
        val sf = fragmentManager?.beginTransaction()
        sf?.setCustomAnimations(R.anim.enter, R.anim.exit)
            ?.replace(R.id.fragment, fragment)?.commit()
        sf?.addToBackStack(null)
        handler.postDelayed({
            activity?.invalidateOptionsMenu()
        }, 50)
        activity?.tvMepet?.text = title
        activity?.nav_view?.setCheckedItem(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.homewithedit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.editPetBtn -> {
                val vm = HomeVM()
                toFragment(vm.newAddPetInstance(id!!), "Edit Pet", R.id.nav_home)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    var id:Int?=null
    var sharPref:SharedPreferences?=null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharPref = context!!.getSharedPreferences("pref",0)
        id = sharPref!!.getInt("id",0)

        cekFoodAndReminder()
        showData()
        val vm = HomeVM()

        //Todo:Ganti ke data binding
        ivProfile.setOnClickListener {
            toFragment(vm.newAddPetInstance(id!!), "Edit Pet", R.id.nav_home)
        }
        btnToShop.setOnClickListener {
            toFragment(ShopFragment(), "MeShop", R.id.nav_meshop)
        }
        layoutFood.setOnClickListener {
            toFragment(ShopFragment(), "MeShop", R.id.nav_meshop)
        }
        btnToReminder.setOnClickListener {
            toFragment(ReminderFragment(), "Reminders", R.id.nav_reminder)
        }
        layoutNextReminder.setOnClickListener {
            toFragment(ReminderFragment(), "Reminders", R.id.nav_reminder)
        }
        cardNextReminder.setOnClickListener {
            //For Ripple Effect
        }
        cardFood.setOnClickListener {
            //For Ripple Effect
        }
        randomRef.setOnClickListener {
            //Same
        }
        val fab: FloatingActionButton? = view?.findViewById(R.id.fab1)
        fab?.setOnClickListener {
            toFragment(AddPetFragment(), "Add Pet", R.id.nav_home)
        }
        fab?.show()
    }
}
