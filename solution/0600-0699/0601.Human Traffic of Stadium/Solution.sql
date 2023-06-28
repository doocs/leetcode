# Write your MySQL query statement below
WITH
    s AS (
        SELECT *, id - row_number() OVER (ORDER BY id) AS rk
        FROM Stadium
        WHERE people >= 100
    ),
    t AS (
        SELECT *, count(*) OVER (PARTITION BY rk) AS cnt
        FROM s
    )
SELECT id, visit_date, people
FROM t
WHERE cnt >= 3
ORDER BY visit_date;
