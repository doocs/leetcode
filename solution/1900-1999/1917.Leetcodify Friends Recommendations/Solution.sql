# Write your MySQL query statement below
WITH
    T AS (
        SELECT user1_id, user2_id FROM Friendship
        UNION
        SELECT user2_id AS user1_id, user1_id AS user2_id FROM Friendship
    )
SELECT DISTINCT l1.user_id, l2.user_id AS recommended_id
FROM
    Listens AS l1,
    Listens AS l2
WHERE
    l1.day = l2.day
    AND l1.song_id = l2.song_id
    AND l1.user_id != l2.user_id
    AND NOT EXISTS (
        SELECT 1
        FROM T AS t
        WHERE l1.user_id = t.user1_id AND l2.user_id = t.user2_id
    )
GROUP BY l1.day, l1.user_id, l2.user_id
HAVING COUNT(DISTINCT l1.song_id) >= 3;
