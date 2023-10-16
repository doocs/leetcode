# Write your MySQL query statement below
SELECT name
FROM Customer
WHERE IFNULL(referee_id, 0) != 2;
