package com.example.pw1.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.BufferedReader
import java.io.InputStreamReader

class DatabaseHelper(private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        executeSQLFile(db, "init_db.sql")
        executeSQLFile(db, "inserting_data.sql") // Insert test data if needed
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        executeSQLFile(db, "onUpgrade.sql")
        onCreate(db)
    }

    private fun executeSQLFile(db: SQLiteDatabase, fileName: String) {
        try {
            val inputStream = context.assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            val statement = StringBuilder()

            while (reader.readLine().also { line = it } != null) {
                // Skip empty lines and comments
                if (line!!.trim().isEmpty() || line!!.trim().startsWith("--")) continue
                statement.append(line).append(" ")
                // Execute SQL when statement ends with ;
                if (line!!.trim().endsWith(";")) {
                    db.execSQL(statement.toString().trim())
                    statement.clear()
                }
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    companion object {
        private const val DATABASE_VERSION = 7
        private const val DATABASE_NAME = "pw1.db"
    }
}
