# Write your MySQL query statement below
SELECT
    name
FROM
    Employee AS e1
    JOIN (
        SELECT
            managerId
        FROM Employee
        WHERE managerId IS NOT NULL
        GROUP BY managerId
        HAVING count(1) >= 5
    ) AS e2
        ON e1.id = e2.managerId;
