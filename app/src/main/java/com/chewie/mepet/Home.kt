package com.chewie.mepet

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        nav_view.getMenu().getItem(1).setChecked(true)
        val fab: FloatingActionButton = findViewById(R.id.fab1)
        fab.setOnClickListener { view ->
            if (supportFragmentManager != null){
                val sf = supportFragmentManager.beginTransaction()
                sf.setCustomAnimations(R.anim.enter,R.anim.exit).replace(R.id.fragment, addPet())
                sf.remove(homeFrag()).commit()
                fab.setEnabled(false)
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

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
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
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Belum ada hehe", Toast.LENGTH_LONG).show()
            }
            R.id.nav_home -> {
                val sf = supportFragmentManager.beginTransaction()
                sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, homeFrag())
                sf.addToBackStack(null)
                sf.remove(addPet()).commit()
                val fab: FloatingActionButton = findViewById(R.id.fab1)
                fab.setEnabled(true)
            }
            R.id.nav_references -> {
                Toast.makeText(this, "Sama ini juga belum", Toast.LENGTH_LONG).show()
            }
            R.id.nav_meshop -> {
                Toast.makeText(this, "Udah, dibilangin belum selesai", Toast.LENGTH_LONG).show()
            }
            R.id.nav_settings -> {
                Toast.makeText(this, "Apalagi ini", Toast.LENGTH_LONG).show()
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
