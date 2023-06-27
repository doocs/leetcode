# Write your MySQL query statement below
SELECT
    *,
    if(x + y > z AND x + z > y AND y + z > x, 'Yes', 'No') AS triangle
FROM Triangle;
