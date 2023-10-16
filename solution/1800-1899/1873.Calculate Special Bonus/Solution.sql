# Write your MySQL query statement below
SELECT
    employee_id,
    IF(
        employee_id % 2 = 0
        OR LEFT(name, 1) = 'M',
        0,
        salary
    ) AS bonus
FROM
    employees
ORDER BY 1;
