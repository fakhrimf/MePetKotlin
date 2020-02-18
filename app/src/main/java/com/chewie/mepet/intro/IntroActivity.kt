package com.chewie.mepet.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.chewie.mepet.Home
import com.chewie.mepet.utils.FIRST_RUN_KEY
import com.github.paolorotolo.appintro.AppIntro

class IntroActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState)

        //  setContentView(R.layout.activity_intro)
        addSlide(fragment_onboarding1())
        addSlide(fragment_onboarding2())
        addSlide(fragment_onborading3())
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        val intent = Intent(this, Home::class.java)
        val sharPref = getSharedPreferences(FIRST_RUN_KEY, Context.MODE_PRIVATE)
        sharPref.edit()?.apply {
            putBoolean(FIRST_RUN_KEY, false)
            apply()
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        this.finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val intent = Intent(this, Home::class.java)
        val sharPref = getSharedPreferences(FIRST_RUN_KEY, Context.MODE_PRIVATE)
        sharPref.edit()?.apply {
            putBoolean(FIRST_RUN_KEY, false)
            apply()
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        this.finish()
    }

    override fun onBackPressed() {
        this.finishAffinity()
    }
}
