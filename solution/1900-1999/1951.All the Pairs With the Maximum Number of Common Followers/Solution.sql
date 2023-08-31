# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            r1.user_id AS user1_id,
            r2.user_id AS user2_id,
            rank() OVER (ORDER BY count(1) DESC) AS rk
        FROM
            Relations AS r1
            JOIN Relations AS r2 ON r1.follower_id = r2.follower_id AND r1.user_id < r2.user_id
        GROUP BY r1.user_id, r2.user_id
    )
SELECT
    user1_id,
    user2_id
FROM t
WHERE rk = 1;
