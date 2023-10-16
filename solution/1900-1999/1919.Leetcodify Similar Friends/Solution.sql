# Write your MySQL query statement below
SELECT DISTINCT user1_id, user2_id
FROM
    Friendship AS f
    LEFT JOIN Listens AS l1 ON user1_id = l1.user_id
    LEFT JOIN Listens AS l2 ON user2_id = l2.user_id
WHERE l1.song_id = l2.song_id AND l1.day = l2.day
GROUP BY 1, 2, l1.day
HAVING COUNT(DISTINCT l1.song_id) >= 3;
