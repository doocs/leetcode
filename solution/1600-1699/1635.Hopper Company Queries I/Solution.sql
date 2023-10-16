# Write your MySQL query statement below
WITH
    recursive Months AS (
        SELECT
            1 AS month
        UNION ALL
        SELECT
            month + 1
        FROM Months
        WHERE month < 12
    ),
    Ride AS (
        SELECT MONTH(requested_at) AS month, COUNT(1) AS cnt
        FROM
            Rides AS r
            JOIN AcceptedRides AS a
                ON r.ride_id = a.ride_id AND YEAR(requested_at) = 2020
        GROUP BY month
    )
SELECT
    m.month,
    COUNT(driver_id) AS active_drivers,
    IFNULL(r.cnt, 0) AS accepted_rides
FROM
    Months AS m
    LEFT JOIN Drivers AS d
        ON (m.month >= MONTH(d.join_date) AND YEAR(d.join_date) = 2020)
        OR YEAR(d.join_date) < 2020
    LEFT JOIN Ride AS r ON m.month = r.month
GROUP BY month;
