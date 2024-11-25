--//step 3 - creating tables

-- 1. Users Table (stores all users)
DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,  -- Primary key, auto-increment
    name TEXT UNIQUE NOT NULL,               -- Unique username
    email TEXT UNIQUE NOT NULL,                  -- Unique email address
    password TEXT NOT NULL                       -- User's password (hashed)
);



-- 2. Moods Table (stores individual mood entries for each user)
DROP TABLE IF EXISTS MoodEntries;
CREATE TABLE MoodEntries (
    mood_id INTEGER PRIMARY KEY AUTOINCREMENT,  -- Primary key, auto-increment
    user_id INTEGER NOT NULL,                   -- Foreign key to the Users table
    mood TEXT CHECK(mood IN ('happy', 'sad', 'stressed', 'angry', 'content')) NOT NULL,  -- Mood type, must be one of the specified values
    note TEXT,                                  -- Optional notes about the mood
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,    -- Timestamp of when the mood is recorded (defaults to current time)
    FOREIGN KEY (user_id) REFERENCES Users(user_id)  -- Foreign key constraint
);




-- 3. Tips Table (stores tips linked to each mood category)
DROP TABLE IF EXISTS Tips;
CREATE TABLE Tips (
    tip_id INTEGER PRIMARY KEY AUTOINCREMENT,    -- Primary key, auto-increment
    mood_category TEXT CHECK(mood_category IN ('happy', 'sad', 'stressed', 'angry', 'content')) NOT NULL, -- Mood category for the tip
    tip_text TEXT NOT NULL                       -- The tip text that can help improve the user's mood
);




-- 4. Monthly Summary Table  (stores the monthly summary for each user)
DROP TABLE IF EXISTS Monthly_Summary;
CREATE TABLE Monthly_Summary (
    summary_id INTEGER PRIMARY KEY AUTOINCREMENT,  -- Primary key, auto-increment
    user_id INTEGER NOT NULL,                      -- Foreign key to the Users table
    month TEXT NOT NULL,                           -- Month for the summary (e.g., '2024-11')
    average_mood TEXT CHECK(average_mood IN ('happy', 'sad', 'stressed', 'angry', 'content')) NOT NULL,  -- Predominant mood of the month
    summary_text TEXT,                             -- Optional text for additional summary notes
    FOREIGN KEY (user_id) REFERENCES Users(user_id)  -- Foreign key constraint
);



























































