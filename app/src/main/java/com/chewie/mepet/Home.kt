package com.chewie.mepet

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
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
                val sf = supportFragmentManager.beginTransaction()
                sf.replace(R.id.fragment, reminderFrag()).commit()
                sf.addToBackStack(null)
                val fab: FloatingActionButton = findViewById(R.id.fab1)
                fab.hide()
                nav_view.setCheckedItem(R.id.nav_reminder)
            } else if (fragmentIntent == "shop") {
                val sf = supportFragmentManager.beginTransaction()
                sf.replace(R.id.fragment, shop()).commit()
                sf.addToBackStack(null)
                val fab: FloatingActionButton = findViewById(R.id.fab1)
                fab.hide()
                nav_view.setCheckedItem(R.id.nav_meshop)
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
//        setChannel()
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
            val sf = supportFragmentManager.beginTransaction()
            sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, homeFrag())
                .commit()
            sf.addToBackStack(null)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val handler = Handler()
        val delay:Long = 300
        when (item.itemId) {
            R.id.nav_profile -> {
                val sf = supportFragmentManager.beginTransaction()
                handler.postDelayed({
                    sf.setCustomAnimations(R.anim.enter, R.anim.exit)
                        .replace(R.id.fragment, profileFrag()).commit()
                    sf.addToBackStack(null)
                }, delay)
                tvMepet.text = "Profile"
            }
            R.id.nav_home -> {
                val sf = supportFragmentManager.beginTransaction()
                handler.postDelayed({
                    sf.setCustomAnimations(R.anim.enter, R.anim.exit)
                        .replace(R.id.fragment, homeFrag())
                        .commit()
                    sf.addToBackStack(null)
                }, delay)
                tvMepet.text = "Home"
            }
            R.id.nav_references -> {
                val sf = supportFragmentManager.beginTransaction()
                handler.postDelayed({
                    sf.setCustomAnimations(R.anim.enter, R.anim.exit)
                        .replace(R.id.fragment, Refere())
                        .commit()
                    sf.addToBackStack(null)
                }, delay)
                tvMepet.text = "References"
            }
            R.id.nav_meshop -> {
                val sf = supportFragmentManager.beginTransaction()
                handler.postDelayed({
                    sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, shop())
                        .commit()
                    sf.addToBackStack(null)
                }, delay)
                tvMepet.text = "MeShop"
            }
            R.id.nav_reminder -> {
                val sf = supportFragmentManager.beginTransaction()
                handler.postDelayed({
                    sf.setCustomAnimations(R.anim.enter, R.anim.exit)
                        .replace(R.id.fragment, reminderFrag()).commit()
                    sf.addToBackStack(null)
                }, delay)
                tvMepet.text = "Reminders"
//                notifyMe() //this is a test
            }
            R.id.nav_aboutus -> {
                val sf = supportFragmentManager.beginTransaction()
                handler.postDelayed({
                    sf.setCustomAnimations(R.anim.enter, R.anim.exit)
                        .replace(R.id.fragment, aboutFrag()).commit()
                    sf.addToBackStack(null)
                }, delay)
                tvMepet.text = "About Us"
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    //Fungsi Notifikasi ada di AlarmReceiver sama reminderFrag
}
