package com.chewie.mepet.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.intro.IntroActivity
import com.chewie.mepet.reminder.ReminderFragment
import com.chewie.mepet.shop.ShopFragment
import com.chewie.mepet.utils.SharedPreference
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private val vm by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(HomeVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
            val profile = db.getReminder(petId)

            if (profile.jamSiang.isEmpty()) tvTime.text = getString(R.string.add)
            else tvTime.text = profile.jamSiang
            if (Calendar.getInstance().timeInMillis >= calSiang.timeInMillis) {
                cekSiang.setImageResource(R.drawable.ic_check_black_24dp)
                if (profile.jamMalam.isEmpty()) tvTime.text = getString(R.string.add)
                else tvTime.text = profile.jamMalam
                ivFood.setImageResource(R.drawable.ic_night)
                if (Calendar.getInstance().timeInMillis >= calMalam.timeInMillis) {
                    cekMalam.setImageResource(R.drawable.ic_check_black_24dp)
                    if (profile.jamPagi.isEmpty()) tvTime.text = getString(R.string.add)
                    else tvTime.text = profile.jamPagi
                    ivFood.setImageResource(R.drawable.ic_morning)
                }
            }
        }
    }

    private fun showData() {
        val dbManager = MepetDatabaseHelper(context)
        val detailProfile = dbManager.getPetById(petId)

        detailProfile?.let {
            tvNama.text = it.petName
            tvAge.text = it.petAge.toString()
            tvWeight.text = getString(R.string.berat, it.petWeight.toString())
            tvJenis.text = it.petType
        }

        if (vm.isLoaded) {
            vm.isLoaded = false
            startActivity(Intent(context, IntroActivity::class.java))
        }
    }

    private fun toFragment(fragment: Fragment, title: String, item: Int) {
        val handler = Handler()
        val sf = parentFragmentManager.beginTransaction()
        sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, fragment).commit()
        sf.addToBackStack(null)
        handler.postDelayed({
            activity?.invalidateOptionsMenu()
        }, 50)
        activity?.tvMepet?.text = title
        activity?.nav_view?.setCheckedItem(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.homewithedit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.editPetBtn -> toFragment(vm.newAddPetInstance(petId), getString(R.string.edit_pet), R.id.nav_home)
        }
        return super.onOptionsItemSelected(item)
    }

    private var petId: Int = 0
    private var sharPref: SharedPreference? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharPref = SharedPreference(requireContext())
        sharPref?.let {
            petId = it.getId()
        }

        cekFoodAndReminder()
        showData()

        ivProfile.setOnClickListener {
            toFragment(vm.newAddPetInstance(petId), getString(R.string.edit_pet), R.id.nav_home)
        }
        btnToShop.setOnClickListener {
            toFragment(ShopFragment(), getString(R.string.meshop), R.id.nav_meshop)
        }
        layoutFood.setOnClickListener {
            toFragment(ShopFragment(), getString(R.string.meshop), R.id.nav_meshop)
        }
        btnToReminder.setOnClickListener {
            toFragment(ReminderFragment(), getString(R.string.reminders), R.id.nav_reminder)
        }
        layoutNextReminder.setOnClickListener {
            toFragment(ReminderFragment(), getString(R.string.reminders), R.id.nav_reminder)
        }
        cardNextReminder.setOnClickListener {
            //For Ripple Effect
        }
        cardFood.setOnClickListener {
            //For Ripple Effect
        }
        randomRef.setOnClickListener {
            //For Ripple Effect
        }
        val fab: FloatingActionButton? = view?.findViewById(R.id.fab1)
        fab?.setOnClickListener {
            toFragment(AddPetFragment(), getString(R.string.add_pet), R.id.nav_home)
        }
        fab?.show()
    }
}
