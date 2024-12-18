--//step 4 - writing test data

--1. Inserting Test Data for Users Table:
-- Insert test users into the Users table
INSERT INTO Users (name, email, password) VALUES
('Alice Johnson', 'alice.johnson@example.com', 'SecurePass123'),
('Bob Smith', 'bob.smith@example.com', 'Password@456'),
('Charlie Davis', 'charlie.davis@example.com', '1234StrongPass'),
('Diana Roberts', 'diana.roberts@example.com', 'MyPass@123'),
('Evan Lee', 'evan.lee@example.com', 'EvanSecure!789');








--2. Inserting Test Data for Moods Table:
-- Test Data 1: User 1 logs various moods
INSERT INTO MoodEntries (user_id, mood, note, date)
VALUES
(1, 'happy', 'Had a great start to the week!', '2024-11-01 08:00:00'),
(1, 'stressed', 'A lot of work at the office today.', '2024-11-02 10:30:00'),
(1, 'content', 'Just relaxed at home in the evening.', '2024-11-03 20:00:00'),
(1, 'angry', 'Had an argument with a colleague.', '2024-11-04 14:15:00'),
(1, 'sad', 'Felt down after the argument.', '2024-11-05 17:45:00');


-- Test Data 2: User 2 logs different moods with varied notes
INSERT INTO MoodEntries (user_id, mood, note, date)
VALUES
(2, 'happy', 'Received good news from a friend.', '2024-11-01 09:00:00'),
(2, 'sad', 'Feeling lonely after a busy week.', '2024-11-02 19:00:00'),
(2, 'angry', 'Frustrated with ongoing issues at work.', '2024-11-03 13:00:00'),
(2, 'content', 'Had a peaceful day off.', '2024-11-04 10:30:00'),
(2, 'stressed', 'Too many tasks and deadlines to meet.', '2024-11-05 15:00:00');


-- Test Data 3: User 3 logs only 'happy' and 'content' moods
INSERT INTO MoodEntries (user_id, mood, note, date)
VALUES
(3, 'happy', 'Spent the day hiking in the mountains.', '2024-11-01 07:00:00'),
(3, 'content', 'Enjoyed a nice meal with family.', '2024-11-02 12:00:00'),
(3, 'happy', 'Had a fun night out with friends.', '2024-11-03 22:00:00'),
(3, 'content', 'Relaxed at home with a good book.', '2024-11-04 18:00:00'),
(3, 'happy', 'Celebrated a personal achievement today!', '2024-11-05 20:00:00');


-- Test Data 4: User 4 logs a variety of moods, some with no notes
INSERT INTO MoodEntries (user_id, mood, note, date)
VALUES
(4, 'stressed', 'Feeling overwhelmed with work and deadlines.', '2024-11-01 08:30:00'),
(4, 'angry', NULL, '2024-11-02 14:00:00'),
(4, 'content', NULL, '2024-11-03 16:00:00'),
(4, 'sad', 'Had an argument with a close friend.', '2024-11-04 11:30:00'),
(4, 'happy', 'Had a relaxing weekend.', '2024-11-05 09:00:00');


-- Test Data 5: User 5 logs moods without notes
INSERT INTO MoodEntries (user_id, mood, note, date)
VALUES
(5, 'happy', NULL, '2024-11-01 08:00:00'),
(5, 'angry', NULL, '2024-11-02 13:30:00'),
(5, 'sad', NULL, '2024-11-03 17:00:00'),
(5, 'content', NULL, '2024-11-04 12:30:00'),
(5, 'stressed', NULL, '2024-11-05 16:00:00');








--3.Inserting Test Data for Tips Table:
-- Insert mood improvement tips for different moods
INSERT INTO Tips (mood_category, tip_text)
VALUES ('happy', 'Keep a gratitude journal to maintain happiness.');

INSERT INTO Tips (mood_category, tip_text)
VALUES ('sad', 'Talk to someone you trust to lift your spirits.');

INSERT INTO Tips (mood_category, tip_text)
VALUES ('stressed', 'Take a break and practice deep breathing.');

INSERT INTO Tips (mood_category, tip_text)
VALUES ('angry', 'Try to take a walk and calm your mind.');

INSERT INTO Tips (mood_category, tip_text)
VALUES ('content', 'Continue enjoying the moment and stay present.');









--4.Inserting Test Data for Monthly Summary Table:
-- Test Data 1: Monthly summary for User 1 (November 2024)
INSERT INTO Monthly_Summary (user_id, month, average_mood, summary_text)
VALUES
(1, '2024-11', 'happy', 'This month, you experienced mostly positive moods, with happiness being the dominant feeling. A few moments of stress were recorded, but overall, the month was uplifting.');

-- Test Data 2: Monthly summary for User 2 (November 2024)
INSERT INTO Monthly_Summary (user_id, month, average_mood, summary_text)
VALUES
(2, '2024-11', 'content', 'This month, your mood was predominantly content, with some instances of stress. It appears you managed to stay calm and balanced throughout.');

-- Test Data 3: Monthly summary for User 3 (November 2024)
INSERT INTO Monthly_Summary (user_id, month, average_mood, summary_text)
VALUES
(3, '2024-11', 'happy', 'Your overall mood this month was positive, with happiness being the most frequent mood. Some sadness and stress were noted, but they were less frequent.');

-- Test Data 4: Monthly summary for User 4 (November 2024)
INSERT INTO Monthly_Summary (user_id, month, average_mood, summary_text)
VALUES
(4, '2024-11', 'sad', 'This month, sadness prevailed in your mood tracking, with moments of happiness scattered throughout. It seems like you had a difficult month, but there were some positive moments as well.');

-- Test Data 5: Monthly summary for User 5 (November 2024)
INSERT INTO Monthly_Summary (user_id, month, average_mood, summary_text)
VALUES
(5, '2024-11', 'stressed', 'The majority of your recorded moods this month were stressed. While there were some moments of contentment, stress was the dominant feeling. Try to take more time for relaxation next month.');

