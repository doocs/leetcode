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
        SELECT month(requested_at) AS month, count(1) AS cnt
        FROM
            Rides AS r
            JOIN AcceptedRides AS a
                ON r.ride_id = a.ride_id AND year(requested_at) = 2020
        GROUP BY month
    )
SELECT
    m.month,
    count(driver_id) AS active_drivers,
    ifnull(r.cnt, 0) AS accepted_rides
FROM
    Months AS m
    LEFT JOIN Drivers AS d
        ON (m.month >= month(d.join_date) AND year(d.join_date) = 2020)
        OR year(d.join_date) < 2020
    LEFT JOIN Ride AS r ON m.month = r.month
GROUP BY month;
