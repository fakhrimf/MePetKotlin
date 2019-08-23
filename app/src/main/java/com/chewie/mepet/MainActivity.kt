package com.chewie.mepet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    private val mWaitHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mWaitHandler.postDelayed({
            try {
                intent = Intent(applicationContext, Home::class.java)
                startActivity(intent)
                finish()
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }, 2000)  // Delay dalam millisekon
    }

    public override fun onDestroy() {
        super.onDestroy()
        mWaitHandler.removeCallbacksAndMessages(null)
    }
}
