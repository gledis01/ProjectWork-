package com.example.pw1.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.pw1.R
import com.example.pw1.data.DatabaseHelper
import com.example.pw1.data.repository.MoodEntryRepository
import com.example.pw1.data.repository.MoodRepository
import com.example.pw1.data.repository.UserRepository
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var moodRepository: MoodRepository
    lateinit var moodEntryRepository: MoodEntryRepository
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fetch and display the logged-in user's name
        val welcomeTextView = findViewById<TextView>(R.id.welcomeText) // Replace with your actual TextView ID
        val userName = getLoggedInUserName() ?: "Guest"
        welcomeTextView.text = "Welcome, $userName"

        // Fetch and display the current date
        val dateTextView = findViewById<TextView>(R.id.currentDateText) // Replace with your actual TextView ID
        val currentDate = getCurrentDate()
        dateTextView.text = currentDate


        // Initialize repositories
        moodRepository = MoodRepository(this)
        moodEntryRepository = MoodEntryRepository(this)
        userRepository = UserRepository(this)

        val moodRadioGroup = findViewById<RadioGroup>(R.id.moodRadioGroup)
        val moodNoteInput = findViewById<EditText>(R.id.moodNoteInput) // Note input
        val submitMoodButton = findViewById<Button>(R.id.submitMoodButton)

        // Submit button logic
        submitMoodButton.setOnClickListener {
            val selectedMoodId = when (moodRadioGroup.checkedRadioButtonId) {
                R.id.moodHappy -> 1
                R.id.moodSad -> 2
                R.id.moodStressed -> 3
                R.id.moodAngry -> 4
                R.id.moodCalm -> 5
                else -> null
            }

            if (selectedMoodId == null) {
                Toast.makeText(this, "Please select a mood", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val moodNote = moodNoteInput.text.toString().trim()
            val user = userRepository.getLoggedInUser()
            if (user == null) {
                Toast.makeText(this, "No logged-in user found.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            val success = moodEntryRepository.addMoodEntry(user.id, selectedMoodId, currentDate, moodNote)

            if (success) {
                Toast.makeText(this, "Mood selected successfully", Toast.LENGTH_SHORT).show()
                showRandomSentence(selectedMoodId) // Show the popup, activity finishes on dialog dismiss
            } else {
                Toast.makeText(this, "Failed to select mood. Only one mood per day allowed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun showRandomSentence(moodId: Int) {
        val sentences = when (moodId) {
            1 -> listOf("Stay positive and spread happiness!", "Keep smiling!")
            2 -> listOf("It’s okay to feel sad. Tomorrow is a new day!", "Talk to someone you trust.")
            3 -> listOf("Take a deep breath. You got this!", "Relax and take a short walk.")
            4 -> listOf("Anger is one letter short of danger. Stay calm!", "Take some time to cool down.")
            5 -> listOf("Enjoy the calm moment. You deserve it!", "Peace of mind is a beautiful thing.")
            else -> listOf("Stay positive!")
        }
        val randomSentence = sentences.random() // Get a random sentence

        val builder = AlertDialog.Builder(this)
        val dialog = builder.setTitle("Mood Selected")
            .setMessage(randomSentence)
            .setCancelable(true) // Allow dismissing on back press or screen tap
            .create()

        // When the dialog is dismissed, finish the activity and return to profile
        dialog.setOnDismissListener {
            finish() // This will finish the activity ONLY after the dialog is dismissed
        }

        dialog.show()
    }




    // Function to fetch the logged-in user's name
    private fun getLoggedInUserName(): String? {
        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.readableDatabase

        var userName: String? = null
        val query = "SELECT name FROM users WHERE is_logged_in = 1" // Query to fetch the logged-in user
        val cursor = db.rawQuery(query, null)

        try {
            if (cursor.moveToFirst()) {
                userName = cursor.getString(0) // Get the user's name
            }
        } finally {
            cursor.close()
            db.close()
        }

        return userName
    }

    // Function to get the current date in a readable format
    private fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()) // Example: "Friday, 15 December 2023"
        return formatter.format(Date()) // Get today's date
    }

}
