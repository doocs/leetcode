# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            sum(frequency) OVER (ORDER BY num ASC) AS rk1,
            sum(frequency) OVER (ORDER BY num DESC) AS rk2,
            sum(frequency) OVER () AS s
        FROM Numbers
    )
SELECT
    round(avg(num), 1) AS median
FROM t
WHERE rk1 >= s / 2 AND rk2 >= s / 2;
