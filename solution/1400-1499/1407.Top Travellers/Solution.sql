SELECT name,
    COALESCE(SUM(distance), 0) AS travelled_distance
FROM Users AS u
    LEFT JOIN Rides AS r ON u.id = r.user_id
GROUP BY
    name
ORDER BY
    travelled_distance DESC,
    name;