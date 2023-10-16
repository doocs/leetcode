# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *
        FROM Friendship
        UNION ALL
        SELECT
            user2_id,
            user1_id
        FROM Friendship
    )
SELECT
    t1.user1_id,
    t1.user2_id,
    COUNT(1) AS common_friend
FROM
    t AS t1
    JOIN t AS t2 ON t1.user2_id = t2.user1_id
    JOIN t AS t3 ON t1.user1_id = t3.user1_id
WHERE t3.user2_id = t2.user2_id AND t1.user1_id < t1.user2_id
GROUP BY t1.user1_id, t1.user2_id
HAVING COUNT(1) >= 3;
