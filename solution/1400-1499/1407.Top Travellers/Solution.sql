# Write your MySQL query statement below
SELECT name, IFNULL(SUM(distance), 0) AS travelled_distance
FROM
    Users AS u
    LEFT JOIN Rides AS r ON u.id = r.user_id
GROUP BY u.id
ORDER BY 2 DESC, 1;
