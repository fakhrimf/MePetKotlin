package com.chewie.mepet

import android.os.Bundle
import android.os.Handler
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.transition.Explode
import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import com.chewie.mepet.home.AddPetFragment
import com.chewie.mepet.home.HomeFragment
import com.chewie.mepet.misc.about.AboutFragment
import com.chewie.mepet.profile.listPetProfile.ListProfileFragment
import com.chewie.mepet.references.ReferencesFragment
import com.chewie.mepet.reminder.ReminderFragment
import com.chewie.mepet.shop.ShopFragment
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
                sf.replace(R.id.fragment, ReminderFragment()).commit()
                sf.addToBackStack(null)
                nav_view.setCheckedItem(R.id.nav_reminder)
            }
        } else {
            val sf = supportFragmentManager.beginTransaction()
            sf.replace(R.id.fragment, HomeFragment()).commit()
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
        } else if (curr is HomeFragment || curr is ShopFragment || curr is ListProfileFragment || curr is AboutFragment || curr is ReminderFragment || curr is ReferencesFragment) {
            if (doubleClick) {
                this.finishAffinity()
            } else {
                Toast.makeText(this, getString(R.string.tap_to_exit), Toast.LENGTH_SHORT).show()
            }
            doubleClick = true
            Handler().postDelayed({ doubleClick = false }, 2000)
        } else if (curr is AddPetFragment) {
            toFragment(HomeFragment(), getString(R.string.home), R.id.nav_home, 0)
        } else {
            super.onBackPressed()
        }
    }

    private fun toFragment(fragment: Fragment, title: String, item: Int, delay: Long) {
        val handler = Handler()
        val sf = supportFragmentManager.beginTransaction()
        handler.postDelayed(
            {
                sf.setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.fragment, fragment).commit()
                sf.addToBackStack(null)
            }, delay)
        tvMepet.text = title
        nav_view.setCheckedItem(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val delay: Long = 330
        when (item.itemId) {
            R.id.nav_profile -> toFragment(ListProfileFragment(), getString(R.string.profile), R.id.nav_profile, delay)
            R.id.nav_home -> toFragment(HomeFragment(), getString(R.string.home), R.id.nav_home, delay)
            R.id.nav_references -> toFragment(ReferencesFragment(), getString(R.string.references), R.id.nav_references, delay)
            R.id.nav_meshop -> toFragment(ShopFragment(), getString(R.string.meshop), R.id.nav_meshop, delay)
            R.id.nav_reminder -> toFragment(ReminderFragment(), getString(R.string.reminders), R.id.nav_reminder, delay)
            R.id.nav_aboutus -> toFragment(AboutFragment(), getString(R.string.about_us), R.id.nav_aboutus, delay)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}
