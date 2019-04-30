package com.example.mvvmtest.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmtest.R
import com.example.mvvmtest.view.activity.ChatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent (this, ChatActivity::class.java)
        startActivity(intent)
        finish()
    }
}
