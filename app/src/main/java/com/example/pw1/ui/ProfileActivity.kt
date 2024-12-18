package com.example.pw1.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.pw1.R
import com.example.pw1.data.repository.MoodEntryRepository
import com.example.pw1.data.repository.UserRepository
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var moodEntryRepository: MoodEntryRepository
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        moodEntryRepository = MoodEntryRepository(this)
        userRepository = UserRepository(this)

        // User Details
        val userNameTextView = findViewById<TextView>(R.id.userNameTextView)
        val userEmailTextView = findViewById<TextView>(R.id.userEmailTextView)

        val user = userRepository.getLoggedInUser()
        if (user != null) {
            userNameTextView.text = "${user.name}"
            userEmailTextView.text = "${user.email}"
        } else {
            userNameTextView.text = "Guest"
            userEmailTextView.text = "No email available"
        }


        // Button to Select Mood
        val moodSelectButton = findViewById<Button>(R.id.moodSelectButton)
        moodSelectButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Calendar to Show Moods
        val moodCalendarView = findViewById<CalendarView>(R.id.moodCalendarView)
        moodCalendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
            showMoodDetailsPopup(selectedDate)
        }

        // Spinner for Month Selection
        val monthSelectorSpinner = findViewById<Spinner>(R.id.monthSelectorSpinner)
        setupMonthSelector(monthSelectorSpinner)
    }

    private fun showMoodDetailsPopup(date: String) {
        val moodEntries = moodEntryRepository.getMoodEntriesByDate(date)
        val builder = AlertDialog.Builder(this)

        if (moodEntries.isEmpty()) {
            builder.setMessage("No mood entry found for this date.")
        } else {
            val moodDetails = moodEntries.joinToString("\n") { "${it.mood.name}: ${it.note}" }
            builder.setMessage(moodDetails)
        }

        builder.setTitle("Mood Details for $date")
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    private fun setupMonthSelector(spinner: Spinner) {
        val months = arrayOf(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, months)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                val selectedMonth = position + 1
                loadMoodEntriesForMonth(selectedMonth)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }



    private fun loadMoodEntriesForMonth(month: Int) {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        // Fetch mood entries
        val moodEntries = moodEntryRepository.getMoodEntriesForMonth(currentYear, month)
        val moodStatistics = moodEntryRepository.getMoodStatisticsForMonth(currentYear, month)

        // Limit to the last 5 mood entries
        val lastFiveEntries = if (moodEntries.size > 5) {
            moodEntries.takeLast(5) // Take the last 5 items
        } else {
            moodEntries // If less than 5, use all entries
        }

        // Display mood entries in the ListView
        val moodHistoryListView = findViewById<ListView>(R.id.moodHistoryListView)
        if (lastFiveEntries.isNotEmpty()) {
            val adapter = MoodHistoryAdapter(this, lastFiveEntries)
            moodHistoryListView.adapter = adapter
        } else {
            Toast.makeText(this, "No mood entries found for this month.", Toast.LENGTH_SHORT).show()
        }

        // Display statistics
        val moodStatsTextView = findViewById<TextView>(R.id.moodStatsTextView)
        if (moodStatistics.isNotEmpty()) {
            val statsText = moodStatistics.entries.joinToString("\n") { "${it.key}: ${it.value} times" }
            moodStatsTextView.text = statsText
        } else {
            moodStatsTextView.text = "No mood statistics available for this month."
        }
    }



}
