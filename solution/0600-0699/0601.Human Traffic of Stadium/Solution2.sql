# Write your MySQL query statement below
WITH
    Consecutive AS (
        SELECT
            *,
            id - ROW_NUMBER() OVER () AS id_diff
        FROM Stadium
        WHERE people >= 100
    )
SELECT id, visit_date, people
FROM Consecutive
WHERE
    id_diff IN (
        SELECT id_diff
        FROM Consecutive
        GROUP BY id_diff
        HAVING COUNT(*) > 2
    )
ORDER BY visit_date;
