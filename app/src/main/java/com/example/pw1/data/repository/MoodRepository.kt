package com.example.pw1.data.repository
import com.example.pw1.data.model.Mood

// Repository class to handle mood data
class MoodRepository {
    // Hardcoded list of moods, could be fetched from a database or API in a real application
    private val moods = listOf(
        Mood(1, "Happy", "Stay positive and spread the joy!"),
        Mood(2, "Sad", "It's okay to feel sad. Take care of yourself."),
        Mood(3, "Angry", "Take a deep breath. Tomorrow is a new day."),
        Mood(4, "Calm", "Enjoy the peace. Keep the calm going.")
    )

    // Function to get all available moods
    fun getAllMoods(): List<Mood> = moods

    // Function to get a specific mood by its ID
    fun getMoodById(id: Int): Mood? = moods.find { it.id == id }
}