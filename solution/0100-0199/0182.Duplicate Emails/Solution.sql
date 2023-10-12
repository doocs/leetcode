# Write your MySQL query statement below
SELECT email
FROM Person
GROUP BY 1
HAVING count(1) > 1;
