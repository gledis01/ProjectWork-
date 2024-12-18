package com.example.pw1.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter
import com.example.pw1.R
import com.example.pw1.data.model.MoodEntry

class MoodHistoryAdapter(private val context: Context, private val moodEntries: List<MoodEntry>) : BaseAdapter() {

    override fun getCount(): Int = moodEntries.size

    override fun getItem(position: Int): Any = moodEntries[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.mood_history_item, parent, false)
        val moodEntry = moodEntries[position]

        // Bind data to the views
        val moodDateText = view.findViewById<TextView>(R.id.moodDateText)
        val moodNameText = view.findViewById<TextView>(R.id.moodNameText)
        val moodNoteText = view.findViewById<TextView>(R.id.moodNoteText)

        moodDateText.text = "Date: ${moodEntry.date}"
        moodNameText.text = "Mood: ${moodEntry.mood.name}"
        moodNoteText.text = "Note: ${moodEntry.note ?: "No note"}"

        return view
    }
}
