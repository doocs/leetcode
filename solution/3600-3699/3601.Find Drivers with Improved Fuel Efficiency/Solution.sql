# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            driver_id,
            AVG(distance_km / fuel_consumed) half_avg,
            CASE
                WHEN MONTH(trip_date) <= 6 THEN 1
                ELSE 2
            END half
        FROM trips
        GROUP BY driver_id, half
    )
SELECT
    t1.driver_id,
    d.driver_name,
    ROUND(t1.half_avg, 2) first_half_avg,
    ROUND(t2.half_avg, 2) second_half_avg,
    ROUND(t2.half_avg - t1.half_avg, 2) efficiency_improvement
FROM
    T t1
    JOIN T t2 ON t1.driver_id = t2.driver_id AND t1.half < t2.half AND t1.half_avg < t2.half_avg
    JOIN drivers d ON t1.driver_id = d.driver_id
ORDER BY efficiency_improvement DESC, d.driver_name;
