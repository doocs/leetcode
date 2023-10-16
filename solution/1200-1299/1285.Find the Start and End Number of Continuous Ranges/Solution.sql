# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            log_id,
            log_id-ROW_NUMBER() OVER (ORDER BY log_id) AS pid
        FROM Logs
    )
SELECT MIN(log_id) AS start_id, MAX(log_id) AS end_id
FROM T
GROUP BY pid;
