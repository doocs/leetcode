# Write your MySQL query statement below
SELECT country
FROM
    (
        SELECT c.name AS country, AVG(duration) AS duration
        FROM
            Person
            JOIN Calls ON id IN (caller_id, callee_id)
            JOIN Country AS c ON LEFT(phone_number, 3) = country_code
        GROUP BY 1
    ) AS t
WHERE duration > (SELECT AVG(duration) FROM Calls);
