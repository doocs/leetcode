# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            LAG(num) OVER () AS a,
            LEAD(num) OVER () AS b
        FROM Logs
    )
SELECT DISTINCT num AS ConsecutiveNums
FROM T
WHERE a = num AND b = num;
