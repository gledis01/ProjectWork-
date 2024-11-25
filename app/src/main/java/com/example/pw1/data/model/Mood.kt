package com.example.pw1.data.model

// Data class representing a Mood with its ID, name, and a personalized message
data class Mood(
    val id: Int,                    // Unique identifier for the mood
    val name: String,               // Name of the mood (e.g., Happy, Sad)
    val personalizedMessage: String // Personalized message for the selected mood
)