# Write your MySQL query statement below
SELECT ROUND(SQRT(POW(p1.x - p2.x, 2) + POW(p1.y - p2.y, 2)), 2) AS shortest
FROM
    Point2D AS p1,
    Point2D AS p2
WHERE p1.x != p2.x OR p1.y != p2.y
ORDER BY 1
LIMIT 1;
