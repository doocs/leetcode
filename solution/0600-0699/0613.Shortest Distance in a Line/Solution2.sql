# Write your MySQL query statement below
SELECT x - LAG(x) OVER (ORDER BY x) AS shortest
FROM Point
ORDER BY 1
LIMIT 1, 1;
