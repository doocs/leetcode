# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            person_name,
            SUM(weight) OVER (ORDER BY turn) AS s
        FROM Queue
    )
SELECT person_name
FROM T
WHERE s <= 1000
ORDER BY s DESC
LIMIT 1;
