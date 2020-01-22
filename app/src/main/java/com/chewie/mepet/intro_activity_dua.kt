package com.chewie.mepet

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chewie.mepet.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_shop.*

class intro_activity_dua : AppCompatActivity(){
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_aplikasi_two)
        textView = findViewById(R.id.nextIntroDua)
        textView.setOnClickListener {
            startActivity(Intent(this, Home ::class.java))
        }
    }

}