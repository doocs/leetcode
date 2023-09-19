# Write your MySQL query statement below
WITH RECURSIVE
    Month AS (
        SELECT 1 AS month
        UNION
        SELECT month + 1
        FROM Month
        WHERE month < 12
    ),
    S AS (
        SELECT month, driver_id, join_date
        FROM
            Month AS m
            LEFT JOIN Drivers AS d
                ON year(d.join_date) < 2020
                OR (year(d.join_date) = 2020 AND month(d.join_date) <= month)
    ),
    T AS (
        SELECT driver_id, requested_at
        FROM
            Rides
            JOIN AcceptedRides USING (ride_id)
        WHERE year(requested_at) = 2020
    )
SELECT
    month,
    ifnull(
        round(count(DISTINCT t.driver_id) * 100 / count(DISTINCT s.driver_id), 2),
        0
    ) AS working_percentage
FROM
    S AS s
    LEFT JOIN T AS t
        ON s.driver_id = t.driver_id
        AND s.join_date <= t.requested_at
        AND s.month = month(t.requested_at)
GROUP BY 1;
