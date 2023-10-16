# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            managerId,
            COUNT(1) OVER (PARTITION BY managerId) AS cnt
        FROM Employee
    )
SELECT DISTINCT name
FROM
    Employee AS e
    JOIN T AS t ON e.id = t.managerId
WHERE cnt >= 5;
