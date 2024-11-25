--ACTUAL QUERIES FOR APP'S FUNCTIONALITIES
/*
--1. CREATE AN ACCOUNT
--// inserts a new user into Users table
INSERT INTO Users (name, email, password)
VALUES ('Delya', 'delyak@gmail.com', 'Delya123');

SELECT * FROM Users;



--2. LOG IN TO A PROFILE
--//checks if a user exists with the provided username and password
SELECT user_id
FROM Users
WHERE name = 'Delya' AND password = 'Delya123';



--3. SELECT A MOOD ONCE A DAY
-- Check if the user already selected a mood today
SELECT COUNT(*) AS MoodCount
FROM MoodEntries
WHERE user_id = 2 AND DATE(date) = DATE('now');

-- Insert the mood if no entry exists for today
INSERT INTO MoodEntries (user_id, mood, note)
SELECT 2, 'happy', 'Feeling great today!'
WHERE NOT EXISTS (
    SELECT 2
    FROM MoodEntries
    WHERE user_id = 2 AND DATE(date) = DATE('now')
);

SELECT * FROM MoodEntries;


--4.ACCESS THE MONTHLY OVERVIEW OF MOODS
SELECT Mood, COUNT(*) AS MoodCount
FROM MoodEntries
WHERE user_id = 1 AND date LIKE '2024-11%'
GROUP BY Mood;



--5.Retrieve an Inspiring Quote or Tip Based on the Selected Mood
SELECT tip_text
FROM Tips
WHERE mood_category = 'happy'
ORDER BY RANDOM()
LIMIT 1;



--6. View a Monthly Summary of Mood Entries
--version 1:
SELECT mood, COUNT(*) AS MoodCount
FROM MoodEntries
WHERE user_id = 1 AND date LIKE '2024-11%'
GROUP BY mood;

--version 2:
SELECT average_mood
FROM Monthly_Summary
WHERE user_id = 1 AND month = '2024-11';

*/
















