package com.example.pw1.ui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pw1.R
import com.example.pw1.data.repository.UserRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userRepository = UserRepository(this)

        // Input fields
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)

        // Submit button
        val submitButton = findViewById<ImageView>(R.id.submitButton)


        // Handle login
        submitButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (validateInputs(email, password)) {
                if (authenticateUser(email, password)) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val mainIntent = Intent(this, ProfileActivity::class.java)
                    startActivity(mainIntent)
                    finish() // Close the login activity
                } else {
                    Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun authenticateUser(email: String, password: String): Boolean {
        val userId = userRepository.getUserIdByEmailAndPassword(email, password)
        return if (userId != null) {
            userRepository.setUserLoggedIn(userId)
            true
        } else {
            false
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Enter a valid email address")
            return false
        }

        if (password.isEmpty() || password.length < 6) {
            showToast("Password must be at least 6 characters")
            return false
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
