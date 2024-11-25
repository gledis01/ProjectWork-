package com.example.pw1.service

import com.example.pw1.data.model.Mood
import com.example.pw1.data.model.MoodEntry
import com.example.pw1.data.repository.MoodRepository
import com.example.pw1.data.repository.MoodEntryRepository

// Service class that interacts with the repositories to manage moods and mood entries
class MoodService(
    private val moodRepository: MoodRepository,               // Dependency on MoodRepository
    private val moodEntryRepository: MoodEntryRepository      // Dependency on MoodEntryRepository
) {
    // Function to get all available moods from the repository
    fun getAvailableMoods(): List<Mood> = moodRepository.getAllMoods()

    // Function to handle mood selection and saving the entry
    fun selectMood(moodId: Int, date: String): String {
        val mood = moodRepository.getMoodById(moodId)  // Find the mood by ID
        return if (mood != null) {
            moodEntryRepository.saveMoodEntry(MoodEntry(date, mood))  // Save the entry
            mood.personalizedMessage  // Return the personalized message for the selected mood
        } else {
            "Invalid mood selection."  // If mood ID is invalid
        }
    }

    // Function to get an overview of moods for a specific month and year
    fun getMonthlyOverview(year: Int, month: Int): Map<Mood, Int> {
        val entries = moodEntryRepository.getMoodEntriesForMonth(year, month)
        return entries.groupingBy { it.mood }.eachCount()  // Count how many times each mood was chosen
    }
}
