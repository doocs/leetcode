# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            left(phone_number, 3) AS country_code,
            avg(duration) AS duration
        FROM
            Person
            JOIN Calls ON id IN (caller_id, callee_id)
        GROUP BY country_code
    )
SELECT
    c.name AS country
FROM
    Country AS c
    JOIN t ON c.country_code = t.country_code
WHERE
    t.duration > (
        SELECT
            avg(duration)
        FROM Calls
    );
