package com.chewie.mepet

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mWaitHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animation = AnimationUtils.loadAnimation(this, R.anim.kekanan)
        kiri.startAnimation(animation)

        val animations = AnimationUtils.loadAnimation(this, R.anim.kekiri)
        kanan.startAnimation(animations)

        window.navigationBarColor = resources.getColor(R.color.colorPrimary, theme)

        mWaitHandler.postDelayed({
            intent = Intent(applicationContext, Home::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }, 2600)  // Delay dalam millisekon
        mWaitHandler.postDelayed({
            finish()
        }, 4000)
    }

    public override fun onDestroy() {
        super.onDestroy()
        mWaitHandler.removeCallbacksAndMessages(null)
    }
}
