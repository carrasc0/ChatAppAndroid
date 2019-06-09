package com.example.mvvmtest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.mvvmtest.R
import kotlinx.android.synthetic.main.activity_preferences.*

class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        setSupportActionBar(toolbar)

    }

}
