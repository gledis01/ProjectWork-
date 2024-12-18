package com.example.pw1.data.model


data class MoodEntry(
    val date: String, // Date of the mood entry
    val mood: Mood,   // Associated mood
    val note: String? // Optional note
)
