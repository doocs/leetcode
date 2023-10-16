# Write your MySQL query statement below
WITH RECURSIVE
    Months AS (
        SELECT 1 AS month
        UNION ALL
        SELECT month + 1
        FROM Months
        WHERE month < 12
    ),
    Ride AS (
        SELECT
            month,
            SUM(IFNULL(ride_distance, 0)) AS ride_distance,
            SUM(IFNULL(ride_duration, 0)) AS ride_duration
        FROM
            Months AS m
            LEFT JOIN Rides AS r
                ON month = MONTH(requested_at) AND YEAR(requested_at) = 2020
            LEFT JOIN AcceptedRides AS a ON r.ride_id = a.ride_id
        GROUP BY month
    )
SELECT
    month,
    ROUND(
        AVG(ride_distance) OVER (ROWS BETWEEN CURRENT ROW AND 2 FOLLOWING),
        2
    ) AS average_ride_distance,
    ROUND(
        AVG(ride_duration) OVER (ROWS BETWEEN CURRENT ROW AND 2 FOLLOWING),
        2
    ) AS average_ride_duration
FROM Ride
ORDER BY month
LIMIT 10;
