# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY gender
                ORDER BY user_id
            ) AS rk1,
            CASE
                WHEN gender = 'female' THEN 0
                WHEN gender = 'other' THEN 1
                ELSE 2
            END AS rk2
        FROM Genders
    )
SELECT user_id, gender
FROM t
ORDER BY rk1, rk2;
