package com.example.pw1.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pw1.R
import com.example.pw1.data.DatabaseHelper

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro) // Load intro layout first

        // Handle button click to navigate to registration
        val button = findViewById<ImageView>(R.id.Button) // Button ID from intro_layout
        button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
