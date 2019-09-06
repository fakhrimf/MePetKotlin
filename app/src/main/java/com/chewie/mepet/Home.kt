package com.chewie.mepet

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initBtn()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        nav_view.getMenu().getItem(1).setChecked(true)
        val fab: FloatingActionButton = findViewById(R.id.fab1)
        fab.setOnClickListener { view ->
            if (supportFragmentManager != null){
                val sf = supportFragmentManager.beginTransaction()
                sf.setCustomAnimations(R.anim.enter,R.anim.exit).replace(R.id.fragment, addPet()).commit()
                fab.hide()
                sf.addToBackStack(null)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }


    private fun initBtn(){
        val toShop: Button = findViewById(R.id.btnToShop)
        toShop.setOnClickListener{
            val sf = supportFragmentManager.beginTransaction()
            sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, shop()).commit()
            sf.addToBackStack(null)
            fab1.hide()
            nav_view.getMenu().getItem(4).setChecked(true)
        }
    }

    private var doubleClick = false
    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val curr = supportFragmentManager.findFragmentById(R.id.fragment)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }else if (curr is homeFrag || curr is shop || curr is profileFrag || curr is profileFrag){
            if (doubleClick){
                this.finish()
            } else {
                Toast.makeText(this, "Tap twice to exit", Toast.LENGTH_SHORT).show()
            }
            doubleClick = true
            Handler().postDelayed({doubleClick = false}, 2000)
        } else if (curr is addPet){
            val sf = supportFragmentManager.beginTransaction()
            sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, homeFrag()).commit()
            sf.addToBackStack(null)
            fab1.show()
        } else {
            super.onBackPressed()
            val curr1 = supportFragmentManager.findFragmentById(R.id.fragment)
            if(curr1 is homeFrag){
                fab1.show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_profile -> {
                val sf = supportFragmentManager.beginTransaction()
                sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, profileFrag()).commit()
                sf.addToBackStack(null)
                val fab: FloatingActionButton = findViewById(R.id.fab1)
                fab.hide()
            }
            R.id.nav_home -> {
                val sf = supportFragmentManager.beginTransaction()
                sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, homeFrag()).commit()
                sf.addToBackStack(null)
                val fab: FloatingActionButton = findViewById(R.id.fab1)
                fab.show()
                initBtn()
            }
            R.id.nav_references -> {
//                TODO:Ton references eusian
                Toast.makeText(this, "Mana ton belum jadi",Toast.LENGTH_LONG).show()
            }
            R.id.nav_meshop -> {
                val sf = supportFragmentManager.beginTransaction()
                sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, shop()).commit()
                sf.addToBackStack(null)
                val fab: FloatingActionButton = findViewById(R.id.fab1)
                fab.hide()
            }
            R.id.nav_reminder -> {
                val sf = supportFragmentManager.beginTransaction()
                sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, reminderFrag()).commit()
                sf.addToBackStack(null)
                val fab: FloatingActionButton = findViewById(R.id.fab1)
                fab.hide()
            }
            R.id.nav_aboutus -> {
                Toast.makeText(this, "Made by Chewie Team", Toast.LENGTH_LONG).show()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
