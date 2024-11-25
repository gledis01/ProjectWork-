package com.example.pw1.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pw1.ui.theme.MoodTrackerTheme
import com.example.pw1.service.MoodService
import com.example.pw1.data.repository.MoodRepository
import com.example.pw1.data.repository.MoodEntryRepository

class MainActivity : ComponentActivity() {

    private lateinit var moodService: MoodService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoodTrackerTheme {
                // Initialize repositories and service
                val moodRepository = MoodRepository()
                val moodEntryRepository = MoodEntryRepository
            }
        }
    }
}
