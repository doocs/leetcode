# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            fuel_type,
            driver_id,
            ROUND(AVG(rating), 2) rating,
            SUM(distance) distance,
            SUM(accidents) accidents
        FROM
            Drivers
            JOIN Vehicles USING (driver_id)
            JOIN Trips USING (vehicle_id)
        GROUP BY fuel_type, driver_id
    ),
    P AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY fuel_type
                ORDER BY rating DESC, distance DESC, accidents
            ) rk
        FROM T
    )
SELECT fuel_type, driver_id, rating, distance
FROM P
WHERE rk = 1
ORDER BY 1;
