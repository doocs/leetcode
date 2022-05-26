# Write your MySQL query statement below
SELECT
    b.unique_id AS unique_id,
    a.name AS name
FROM
    Employees a
LEFT JOIN
    EmployeeUNI b
ON
    a.id = b.id;