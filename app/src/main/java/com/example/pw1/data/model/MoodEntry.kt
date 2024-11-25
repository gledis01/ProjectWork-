package com.example.pw1.data.model

// Data class representing a MoodEntry, which records a mood for a specific date
data class MoodEntry(
    val date: String, // Date in the format "YYYY-MM-DD"
    val mood: Mood    // Associated mood for the day
)