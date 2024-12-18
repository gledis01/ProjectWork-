CREATE TABLE users (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        email TEXT NOT NULL UNIQUE,
        password TEXT NOT NULL,
        is_logged_in INTEGER DEFAULT 0
);

CREATE TABLE moods (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        mood_name TEXT NOT NULL UNIQUE
);

CREATE TABLE mood_entries (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        user_id INTEGER NOT NULL,
        date TEXT NOT NULL,
        note TEXT,
        mood_id INTEGER NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
        FOREIGN KEY (mood_id) REFERENCES moods(id) ON DELETE CASCADE,
        UNIQUE(user_id, date)
);

CREATE TABLE sentences (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        sentence TEXT NOT NULL,
        mood_id INTEGER NOT NULL,
        FOREIGN KEY (mood_id) REFERENCES moods(id) ON DELETE CASCADE
);

INSERT INTO moods (mood_name) VALUES
('Happy'),
('Sad'),
('Stressed'),
('Angry'),
('Calm');
