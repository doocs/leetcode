# Write your MySQL query statement below
SELECT name, bonus
FROM
    Employee
    LEFT JOIN Bonus USING (empId)
WHERE ifnull(bonus, 0) < 1000;
