# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            sum(free = 1) OVER (
                ORDER BY seat_id
                ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING
            ) AS cnt
        FROM Cinema
    )
SELECT seat_id
FROM T
WHERE free = 1 AND cnt > 1
ORDER BY 1;
