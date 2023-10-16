# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            business_id,
            occurences > AVG(occurences) OVER (PARTITION BY event_type) AS mark
        FROM Events
    )
SELECT business_id
FROM T
WHERE mark = 1
GROUP BY 1
HAVING COUNT(1) > 1;
