package com.chewie.mepet.view

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.transition.Explode
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import com.chewie.mepet.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*


class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            // set an exit transition
            enterTransition = Explode()
            exitTransition = Explode()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fragmentIntent = intent?.extras?.getString("fragment")
        if (fragmentIntent != null) {
            if (fragmentIntent == "reminder") {
//                Toast.makeText(this, "Opened Reminder", Toast.LENGTH_SHORT).show()
                val sf = supportFragmentManager.beginTransaction()
                sf.replace(R.id.fragment, reminderFrag()).commit()
                sf.addToBackStack(null)
                nav_view.setCheckedItem(R.id.nav_reminder)
            }
        } else {
            val sf = supportFragmentManager.beginTransaction()
            sf.replace(R.id.fragment, homeFrag()).commit()
            sf.addToBackStack(null)
            nav_view.setCheckedItem(R.id.nav_home)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    private var doubleClick = false
    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val curr = supportFragmentManager.findFragmentById(R.id.fragment)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (curr is homeFrag || curr is shop || curr is profileFrag || curr is aboutFrag || curr is reminderFrag) {
            if (doubleClick) {
                this.finishAffinity()
            } else {
                Toast.makeText(this, "Tap twice to exit", Toast.LENGTH_SHORT).show()
            }
            doubleClick = true
            Handler().postDelayed({ doubleClick = false }, 2000)
        } else if (curr is addPet) {
            toFragment(homeFrag(),"Home", R.id.nav_home, 0)
//            fab1.show()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val curr = supportFragmentManager.findFragmentById(R.id.fragment)
        if (curr is homeFrag) {
            menuInflater.inflate(R.menu.homewithedit, menu)
        }
//        Toast.makeText(this, "onPrepareOptionsMenu called.", Toast.LENGTH_SHORT).show()
        return super.onPrepareOptionsMenu(menu)
    }

    private fun toFragment(fragment: Fragment, title:String, item:Int, delay:Long){
        val handler = Handler()
        val sf = supportFragmentManager.beginTransaction()
        handler.postDelayed({
            sf.setCustomAnimations(R.anim.enter, R.anim.exit)
                .replace(R.id.fragment, fragment).commit()
            sf.addToBackStack(null)
        }, delay)
        handler.postDelayed({
            invalidateOptionsMenu()
        }, delay + 50)
        tvMepet.text = title
        nav_view.setCheckedItem(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val handler = Handler()
        val delay: Long = 300
        when (item.itemId) {
            R.id.nav_profile -> toFragment(
                profileFrag(),"Profile",
                R.id.nav_profile,delay)
            R.id.nav_home -> toFragment(
                homeFrag(),"Home",
                R.id.nav_home,delay)
            R.id.nav_references -> toFragment(References(), "References",
                R.id.nav_references,delay)
            R.id.nav_meshop -> toFragment(
                shop(), "MeShop",
                R.id.nav_meshop,delay)
            R.id.nav_reminder -> toFragment(
                reminderFrag(),"Reminders",
                R.id.nav_reminder,delay)
            R.id.nav_aboutus -> toFragment(aboutFrag(),"About Us",
                R.id.nav_aboutus,delay)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun newInstance(id:Int): addPet {
        val args = Bundle()
        args.putInt("id",id)
        val addpet = addPet()
        addpet.arguments = args
        return addpet
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.editPetBtn -> {
                val id = 1
                newInstance(id)
//                Toast.makeText(this, "$id",Toast.LENGTH_SHORT).show()
                toFragment(newInstance(id),"Edit Pet", R.id.nav_home, 0)
                Handler().postDelayed({
                    invalidateOptionsMenu()
                },50)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Fungsi Notifikasi ada di AlarmReceiver sama reminderFrag
}
