package com.example.pw1.data.repository

import android.content.Context
import com.example.pw1.data.DatabaseHelper
import com.example.pw1.data.model.Mood

class MoodRepository(private val context: Context) {

    // Get all predefined moods from the database
    fun getAllMoods(): List<Mood> {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.readableDatabase
        val moods = mutableListOf<Mood>()

        val query = """
    SELECT me.date, me.note, m.id, m.mood_name, '' AS personalized_message
    FROM mood_entries me
    INNER JOIN moods m ON me.mood_id = m.id
    ORDER BY me.date DESC
"""

        val cursor = db.rawQuery(query, null)

        try {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val personalizedMessage = cursor.getString(2)
                moods.add(Mood(id, name, personalizedMessage))
            }
        } finally {
            cursor.close()
            db.close()
        }

        return moods
    }

    // Get a mood by its ID
    fun getMoodById(id: Int): Mood? {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.readableDatabase

        val query = "SELECT id, mood_name, '' AS personalized_message FROM moods WHERE id = ?"
        val cursor = db.rawQuery(query, arrayOf(id.toString()))

        return try {
            if (cursor.moveToFirst()) {
                val name = cursor.getString(1)
                val personalizedMessage = cursor.getString(2)
                Mood(id, name, personalizedMessage)
            } else {
                null
            }
        } finally {
            cursor.close()
            db.close()
        }
    }
}
