# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            session_status,
            status_time,
            LEAD(status_time) OVER (
                PARTITION BY server_id
                ORDER BY status_time
            ) AS next_status_time
        FROM Servers
    )
SELECT FLOOR(SUM(TIMESTAMPDIFF(SECOND, status_time, next_status_time)) / 86400) AS total_uptime_days
FROM T
WHERE session_status = 'start';
