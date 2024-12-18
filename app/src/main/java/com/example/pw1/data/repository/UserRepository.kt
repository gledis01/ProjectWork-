package com.example.pw1.data.repository

import android.content.Context
import com.example.pw1.data.DatabaseHelper
import com.example.pw1.data.model.User

class UserRepository(private val context: Context) {

    fun getLoggedInUser(): User? {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.readableDatabase
        var user: User? = null

        val query = "SELECT id, name, email FROM users WHERE is_logged_in = 1"
        val cursor = db.rawQuery(query, null)

        try {
            if (cursor.moveToFirst()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                user = User(id, name, email)
            }
        } finally {
            cursor.close()
            db.close()
        }

        return user
    }

    // Verify if a user exists by email and password
    fun getUserIdByEmailAndPassword(email: String, password: String): Int? {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.readableDatabase

        val query = "SELECT id FROM users WHERE email = ? AND password = ?"
        val cursor = db.rawQuery(query, arrayOf(email, password))

        return try {
            if (cursor.moveToFirst()) {
                cursor.getInt(0)
            } else {
                null
            }
        } finally {
            cursor.close()
            db.close()
        }
    }

    // Mark a user as logged in
    fun setUserLoggedIn(userId: Int) {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("UPDATE users SET is_logged_in = 0") // Logout all users
            db.execSQL("UPDATE users SET is_logged_in = 1 WHERE id = ?", arrayOf(userId.toString()))
        } finally {
            db.close()
        }
    }
}
