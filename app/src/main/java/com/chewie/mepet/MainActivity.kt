package com.chewie.mepet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mWaitHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animation = AnimationUtils.loadAnimation(this,R.anim.kekanan)
        kiri.startAnimation(animation)

        val animations = AnimationUtils.loadAnimation(this,R.anim.kekiri)
        kanan.startAnimation(animations)


        mWaitHandler.postDelayed({
            try {
                intent = Intent(applicationContext, Home::class.java)
                startActivity(intent)
                finish()
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }, 2600)  // Delay dalam millisekon
    }

    public override fun onDestroy() {
        super.onDestroy()
        mWaitHandler.removeCallbacksAndMessages(null)
    }
}
