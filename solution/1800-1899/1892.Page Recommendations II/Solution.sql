# Write your MySQL query statement below
WITH
    S AS (
        SELECT * FROM Friendship
        UNION
        SELECT user2_id, user1_id FROM Friendship
    )
SELECT user1_id AS user_id, page_id, COUNT(1) AS friends_likes
FROM
    S AS s
    LEFT JOIN Likes AS l ON s.user2_id = l.user_id
WHERE
    NOT EXISTS (
        SELECT 1
        FROM Likes AS l2
        WHERE user1_id = l2.user_id AND l.page_id = l2.page_id
    )
GROUP BY user1_id, page_id;
