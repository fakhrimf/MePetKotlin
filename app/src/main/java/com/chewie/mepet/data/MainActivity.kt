package com.chewie.mepet.data

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import com.chewie.mepet.R
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


        mWaitHandler.postDelayed({
            try {
                intent = Intent(applicationContext, Home::class.java)
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }, 2600)  // Delay dalam millisekon
        mWaitHandler.postDelayed({
            finish()
        },4000)
    }

    public override fun onDestroy() {
        super.onDestroy()
        mWaitHandler.removeCallbacksAndMessages(null)
    }
}