package com.chewie.mepet

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.db.MepetDatabaseHelper
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

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

    private fun cekFoodAndReminder() {
        val sdfJam = SimpleDateFormat("HH", Locale.US)
        val sdfMenit = SimpleDateFormat("mm", Locale.US)
        var jamPagiDate: Date = sdfJam.parse("07")
        var menitPagiDate: Date = sdfMenit.parse("00")

        var jamSiangDate: Date = sdfJam.parse("12")
        var menitSiangDate: Date = sdfMenit.parse("00")

        var jamMalamDate: Date = sdfJam.parse("20")
        var menitMalamDate: Date = sdfMenit.parse("00")


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
            tvTime.text = "12:00" //Next Reminder in db Todo: Implementasi Waktu dari database
            if (Calendar.getInstance().timeInMillis >= calSiang.timeInMillis) {
                cekSiang.setImageResource(R.drawable.ic_check_black_24dp)
                tvTime.text = "20:00"
                ivFood.setImageResource(R.drawable.ic_night)
                if (Calendar.getInstance().timeInMillis >= calMalam.timeInMillis) {
                    cekMalam.setImageResource(R.drawable.ic_check_black_24dp)
                    tvTime.text = "07:00"
                    ivFood.setImageResource(R.drawable.ic_morning)
                }
            }
        }
    }
    private fun showData(){
        val dbManager = MepetDatabaseHelper(context)
        val id = 1
        val detailProfile = dbManager.getPetById(id)

        tvNama.text = detailProfile.pet_name
        tvAge.text = detailProfile.pet_age.toString()
        tvWeight.text = detailProfile.pet_weight.toString()+" Kg"
        tvJenis.text = detailProfile.pet_type
        Log.v("Berat",detailProfile.pet_weight.toString())
    }

    private fun toFragment(fragment: Fragment, title:String, item:Int){
        val handler = Handler()
        val delay: Long = 300
        val sf = fragmentManager?.beginTransaction()
        handler.postDelayed({
            sf?.setCustomAnimations(R.anim.enter, R.anim.exit)
                ?.replace(R.id.fragment, fragment)?.commit()
            sf?.addToBackStack(null)
        }, delay)
        handler.postDelayed({
            activity?.invalidateOptionsMenu()
        }, delay + 50)
        activity?.tvMepet?.text = title
        activity?.nav_view?.setCheckedItem(item)
    }

    private fun toAddPet(id: Int): addPet {
        val args = Bundle()
        args.putInt("id", id)
        val addpet = addPet()
        addpet.arguments = args
        return addpet
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cekFoodAndReminder()
        showData()
        val id = 1

        ivProfile.setOnClickListener {
            toFragment(toAddPet(id), "Edit Pet", R.id.nav_home)
        }
        btnToShop.setOnClickListener{
            toFragment(shop(), "MeShop", R.id.nav_meshop)
        }
        layoutFood.setOnClickListener {
            toFragment(shop(), "MeShop", R.id.nav_meshop)
        }
        btnToReminder.setOnClickListener{
            toFragment(reminderFrag(), "Reminders", R.id.nav_reminder)
        }
        layoutNextReminder.setOnClickListener {
            toFragment(reminderFrag(), "Reminders", R.id.nav_reminder)
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
            toFragment(addPet(), "Add Pet", R.id.nav_home)
        }
        fab?.show()
    }
}
