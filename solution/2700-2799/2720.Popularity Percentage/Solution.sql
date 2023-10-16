# Write your MySQL query statement below
WITH
    F AS (
        SELECT * FROM Friends
        UNION
        SELECT user2, user1 FROM Friends
    ),
    T AS (SELECT COUNT(DISTINCT user1) AS cnt FROM F)
SELECT DISTINCT
    user1,
    ROUND(
        (COUNT(1) OVER (PARTITION BY user1)) * 100 / (SELECT cnt FROM T),
        2
    ) AS percentage_popularity
FROM F
ORDER BY 1;
