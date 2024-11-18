
--step 4: writing quieries
-- Query 1: Insert a New Mood Entry
INSERT INTO Moods (user_id, mood, note) --inserts the new entry into the Moods table
VALUES (1, 'happy', 'Feeling great today!');

SELECT * FROM Moods; --gives you the new outcome of Moods tableMonthly_Summary



--Query 2: Retrieve All Moods Logged by a User (here: for user_id 1)
SELECT mood, note, date
FROM Moods
WHERE user_id = 1 --you can replace with another user_id
ORDER BY date DESC;



--Query 3: Summarize Moods for the Month (To provide a summary of a user’s moods for a specific month)
SELECT mood, COUNT(*) AS mood_count
FROM Moods
WHERE user_id = 1 AND date BETWEEN '2024-11-01' AND '2024-11-30'
GROUP BY mood;



--Query 4: Update a Mood Entry (If the user edits their mood or note)
SELECT * FROM Moods; --output before changes

UPDATE Moods
SET mood = 'content', note = 'Relaxing at home'
WHERE mood_id = 1; -- Replace with the actual mood_id

SELECT * FROM Moods; --output after changes



--Query 5: Delete a Mood Entry (If the user deletes a mood entry)
SELECT * FROM Moods;

DELETE FROM Moods
WHERE mood_id = 1;

SELECT * FROM Moods;




--Query 6: Retrieve User Information (If you need to fetch user profile data) --> gives you username and email of the user_id 1
SELECT name, email
FROM Users
WHERE user_id = 1;



--/NO NEED
--Query 7: Fetch a User’s Mood Summary for a Month (Provides a quick summary of moods for a specific month, including total entries and the most frequently logged mood)
SELECT
    COUNT(*) AS total_moods_logged,
    mood,
    COUNT(mood) AS frequency
FROM Moods
WHERE user_id = 1 AND date BETWEEN '2024-11-01' AND '2024-11-30'
GROUP BY mood
ORDER BY frequency DESC





--Query 8: Count Total Entries Logged by a User (Helps in tracking the user’s engagement with the app)
SELECT COUNT(*) AS total_entries
FROM Moods
WHERE user_id = 1;




--Query 9: Retrieve All Users and Their Most Recent Mood (Shows the latest mood logged by each user)
SELECT u.name, m.mood, m.date
FROM Users u
JOIN Moods m ON u.user_id = m.user_id
WHERE m.date = (
    SELECT MAX(date)
    FROM Moods
    WHERE user_id = u.user_id
);



--Query 10: Check for Missing Data (Finds users who haven’t logged any moods yet)
SELECT u.name
FROM Users u
LEFT JOIN Moods m ON u.user_id = m.user_id
WHERE m.user_id IS NULL;



-- Query 11: Generate a List of Users Who Logged a Specific Mood (Shows all users who have ever logged a specific mood, like "stressed.")
SELECT DISTINCT u.name
FROM Users u
JOIN Moods m ON u.user_id = m.user_id
WHERE m.mood = 'stressed';


--Query 12: Give Tips for Mood Improvement
SELECT tip_text
FROM Tips
WHERE mood_category = 'sad';
