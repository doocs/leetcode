# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            log_id,
            log_id-row_number() OVER (ORDER BY log_id) AS pid
        FROM Logs
    )
SELECT min(log_id) AS start_id, max(log_id) AS end_id
FROM T
GROUP BY pid;
