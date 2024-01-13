SELECT
    IFNULL(
        ROUND(COUNT(DISTINCT session_id) / COUNT(DISTINCT user_id), 2),
        0
    ) AS average_sessions_per_user
FROM Activity
WHERE DATEDIFF('2019-07-27', activity_date) < 30;
