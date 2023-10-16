# Write your MySQL query statement below
DELETE FROM Person
WHERE id NOT IN (SELECT MIN(id) FROM (SELECT * FROM Person) AS p GROUP BY email);
