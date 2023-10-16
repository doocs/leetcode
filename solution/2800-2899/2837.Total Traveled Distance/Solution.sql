# Write your MySQL query statement below
SELECT user_id, name, IFNULL(SUM(distance), 0) AS 'traveled distance'
FROM
    Users
    LEFT JOIN Rides USING (user_id)
GROUP BY 1
ORDER BY 1;
