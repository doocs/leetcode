# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            MAX(height) OVER (ORDER BY id) AS l,
            MAX(height) OVER (ORDER BY id DESC) AS r
        FROM Heights
    )
SELECT SUM(LEAST(l, r) - height) AS total_trapped_water
FROM T;
