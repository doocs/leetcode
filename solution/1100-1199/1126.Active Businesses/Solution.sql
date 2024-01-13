# Write your MySQL query statement below
SELECT business_id
FROM
    EVENTS AS t1
    JOIN (
        SELECT
            event_type,
            AVG(occurences) AS occurences
        FROM EVENTS
        GROUP BY event_type
    ) AS t2
        ON t1.event_type = t2.event_type
WHERE t1.occurences > t2.occurences
GROUP BY business_id
HAVING COUNT(1) > 1;
