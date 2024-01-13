# Write your MySQL query statement below
SELECT DISTINCT page_id AS recommended_page
FROM Likes
WHERE
    user_id IN (
        SELECT user1_id AS user_id FROM Friendship WHERE user2_id = 1
        UNION ALL
        SELECT user2_id AS user_id FROM Friendship WHERE user1_id = 1
    )
    AND page_id NOT IN (SELECT page_id FROM Likes WHERE user_id = 1);
