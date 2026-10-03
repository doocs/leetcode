# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            server_id,
            status_time,
            session_status,
            SUM(IF(session_status = 'start', 1, -1)) OVER (
                PARTITION BY server_id
                ORDER BY status_time, session_status
                ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
            ) AS depth
        FROM Servers
    )
SELECT FLOOR(SUM(TIMESTAMPDIFF(SECOND, start_time, stop_time)) / 86400) AS total_uptime_days
FROM
    (
        SELECT
            s.status_time AS start_time,
            MIN(t.status_time) AS stop_time
        FROM
            T AS s
            JOIN T AS t
                ON s.server_id = t.server_id
                AND t.session_status = 'stop'
                AND t.depth = s.depth - 1
                AND t.status_time > s.status_time
        WHERE s.session_status = 'start'
        GROUP BY s.server_id, s.status_time
    ) AS p;
