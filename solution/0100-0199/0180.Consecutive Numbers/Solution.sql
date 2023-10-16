# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            CASE
                WHEN (LAG(num) OVER (ORDER BY id)) = num THEN 0
                ELSE 1
            END AS mark
        FROM Logs
    ),
    p AS (SELECT num, SUM(mark) OVER (ORDER BY id) AS gid FROM t)
SELECT DISTINCT num AS ConsecutiveNums
FROM p
GROUP BY gid
HAVING COUNT(1) >= 3;
