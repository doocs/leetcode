# Write your MySQL query statement below
SELECT max(salary) AS SecondHighestSalary
FROM Employee
WHERE salary < (SELECT max(salary) FROM Employee);
