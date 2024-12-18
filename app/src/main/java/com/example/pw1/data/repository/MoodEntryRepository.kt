package com.example.pw1.data.repository

import android.content.Context
import com.example.pw1.data.DatabaseHelper
import com.example.pw1.data.model.Mood
import com.example.pw1.data.model.MoodEntry

class MoodEntryRepository(private val context: Context) {

    // Save a mood entry to the database
    fun saveMoodEntry(mood: Mood, date: String, note: String? = null): Boolean {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.writableDatabase

        return try {
            val query = "INSERT INTO mood_logs (mood_id, log_date, note) VALUES (?, ?, ?)"
            val statement = db.compileStatement(query)
            statement.bindLong(1, mood.id.toLong())
            statement.bindString(2, date)
            if (note != null) {
                statement.bindString(3, note)
            } else {
                statement.bindNull(3)
            }
            statement.executeInsert() > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            db.close()
        }
    }

    // Get all mood entries for a specific month and year
    fun getMoodEntriesForMonth(year: Int, month: Int): List<MoodEntry> {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.readableDatabase
        val entries = mutableListOf<MoodEntry>()

        val startDate = String.format("%04d-%02d-01", year, month)
        val endDate = String.format("%04d-%02d-31", year, month)

        val query = """
        SELECT me.date, me.note, m.id, m.mood_name
        FROM mood_entries me
        JOIN moods m ON me.mood_id = m.id
        WHERE me.date BETWEEN ? AND ?
        ORDER BY me.date ASC
    """
        val cursor = db.rawQuery(query, arrayOf(startDate, endDate))

        try {
            while (cursor.moveToNext()) {
                val date = cursor.getString(cursor.getColumnIndexOrThrow("date"))
                val note = cursor.getString(cursor.getColumnIndexOrThrow("note"))
                val moodId = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val moodName = cursor.getString(cursor.getColumnIndexOrThrow("mood_name"))

                val mood = Mood(moodId, moodName, "")
                entries.add(MoodEntry(date, mood, note))
            }
        } finally {
            cursor.close()
            db.close()
        }

        return entries
    }

    fun getMoodStatisticsForMonth(year: Int, month: Int): Map<String, Int> {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.readableDatabase
        val moodCounts = mutableMapOf<String, Int>()

        val startDate = String.format("%04d-%02d-01", year, month)
        val endDate = String.format("%04d-%02d-31", year, month)

        val query = """
        SELECT m.mood_name, COUNT(me.mood_id) AS count
        FROM mood_entries me
        JOIN moods m ON me.mood_id = m.id
        WHERE me.date BETWEEN ? AND ?
        GROUP BY me.mood_id
        ORDER BY count DESC
    """

        val cursor = db.rawQuery(query, arrayOf(startDate, endDate))

        try {
            while (cursor.moveToNext()) {
                val moodName = cursor.getString(cursor.getColumnIndexOrThrow("mood_name"))
                val count = cursor.getInt(cursor.getColumnIndexOrThrow("count"))
                moodCounts[moodName] = count
            }
        } finally {
            cursor.close()
            db.close()
        }

        return moodCounts
    }

    fun getMoodEntriesByDate(date: String): List<MoodEntry> {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.readableDatabase
        val entries = mutableListOf<MoodEntry>()

        val query = """
        SELECT me.date, me.note, m.id, m.mood_name
        FROM mood_entries me
        JOIN moods m ON me.mood_id = m.id
        WHERE me.date = ?
    """
        val cursor = db.rawQuery(query, arrayOf(date))

        try {
            while (cursor.moveToNext()) {
                val moodId = cursor.getInt(2)
                val moodName = cursor.getString(3)
                val note = cursor.getString(1)
                entries.add(MoodEntry(date, Mood(moodId, moodName, ""), note))
            }
        } finally {
            cursor.close()
            db.close()
        }
        return entries
    }

    fun addMoodEntry(userId: Int, moodId: Int, date: String, note: String?): Boolean {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.writableDatabase

        return try {
            val query = "INSERT OR IGNORE INTO mood_entries (user_id, mood_id, date, note) VALUES (?, ?, ?, ?)"
            val statement = db.compileStatement(query)
            statement.bindLong(1, userId.toLong())
            statement.bindLong(2, moodId.toLong())
            statement.bindString(3, date)
            if (note != null) {
                statement.bindString(4, note)
            } else {
                statement.bindNull(4)
            }

            statement.executeInsert() > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            db.close()
        }
    }


}
