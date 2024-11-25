package com.example.pw1.data.repository
import com.example.pw1.data.model.MoodEntry

// Repository class to handle mood entry data (storing moods chosen on specific dates)
class MoodEntryRepository {
    private val moodEntries = mutableListOf<MoodEntry>()

    // Function to save a mood entry (called when a user selects a mood)
    fun saveMoodEntry(entry: MoodEntry) {
        moodEntries.add(entry)
    }

    // Function to get all mood entries for a specific month and year
    fun getMoodEntriesForMonth(year: Int, month: Int): List<MoodEntry> {
        return moodEntries.filter {
            val entryDate = it.date.split("-")
            val entryYear = entryDate[0].toInt()
            val entryMonth = entryDate[1].toInt()
            entryYear == year && entryMonth == month
        }
    }
}