# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            IF(num = (LAG(num) OVER ()), 0, 1) AS st
        FROM Logs
    ),
    S AS (
        SELECT *, SUM(st) OVER (ORDER BY id) AS p
        FROM T
    )
SELECT DISTINCT num AS ConsecutiveNums
FROM S
GROUP BY p
HAVING COUNT(1) >= 3;
