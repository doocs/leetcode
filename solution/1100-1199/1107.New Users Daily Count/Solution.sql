# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            user_id,
            MIN(activity_date) OVER (PARTITION BY user_id) AS login_date
        FROM Traffic
        WHERE activity = 'login'
    )
SELECT login_date, COUNT(DISTINCT user_id) AS user_count
FROM T
WHERE DATEDIFF('2019-06-30', login_date) <= 90
GROUP BY 1;
