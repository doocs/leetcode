# Write your MySQL query statement below
SELECT class
FROM Courses
GROUP BY 1
HAVING COUNT(1) >= 5;
