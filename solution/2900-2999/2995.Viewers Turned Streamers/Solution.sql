# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            user_id,
            session_type,
            RANK() OVER (
                PARTITION BY user_id
                ORDER BY session_start
            ) AS rk
        FROM Sessions
    )
SELECT user_id, COUNT(1) AS sessions_count
FROM
    T AS t
    JOIN Sessions AS s USING (user_id)
WHERE rk = 1 AND t.session_type = 'Viewer' AND s.session_type = 'Streamer'
GROUP BY 1
ORDER BY 2 DESC, 1 DESC;
