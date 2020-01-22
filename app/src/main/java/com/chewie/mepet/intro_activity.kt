package com.chewie.mepet

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class intro_activity : AppCompatActivity(){
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_aplikasi)
        textView = findViewById(R.id.nextIntro)
        textView.setOnClickListener {
            startActivity(Intent(this, intro_activity_dua::class.java))
        }
    }

}