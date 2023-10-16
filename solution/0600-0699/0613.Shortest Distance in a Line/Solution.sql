# Write your MySQL query statement below
SELECT MIN(p2.x - p1.x) AS shortest
FROM
    Point AS p1
    JOIN Point AS p2 ON p1.x < p2.x;
