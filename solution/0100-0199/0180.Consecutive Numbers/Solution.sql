# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            CASE
                WHEN (lag(num) OVER (ORDER BY id)) = num THEN 0
                ELSE 1
            END AS mark
        FROM Logs
    ),
    p AS (SELECT num, sum(mark) OVER (ORDER BY id) AS gid FROM t)
SELECT DISTINCT num AS ConsecutiveNums
FROM p
GROUP BY gid
HAVING count(1) >= 3;
