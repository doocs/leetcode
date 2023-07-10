# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            seat_id,
            (free + (lag(free) OVER (ORDER BY seat_id))) AS a,
            (free + (lead(free) OVER (ORDER BY seat_id))) AS b
        FROM Cinema
    )
SELECT seat_id
FROM T
WHERE a = 2 OR b = 2;
