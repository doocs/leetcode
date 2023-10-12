# Write your MySQL query statement below
WITH
    T AS (
        SELECT c.name AS country, avg(duration) AS duration
        FROM
            Person
            JOIN Calls ON id IN (caller_id, callee_id)
            JOIN Country AS c ON left(phone_number, 3) = country_code
        GROUP BY 1
    )
SELECT country
FROM T
WHERE duration > (SELECT avg(duration) FROM Calls);
