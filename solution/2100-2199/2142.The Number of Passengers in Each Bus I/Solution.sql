# Write your MySQL query statement below
SELECT
    bus_id,
    COUNT(passenger_id) - LAG(COUNT(passenger_id), 1, 0) OVER (
        ORDER BY b.arrival_time
    ) AS passengers_cnt
FROM
    Buses AS b
    LEFT JOIN Passengers AS p ON p.arrival_time <= b.arrival_time
GROUP BY 1
ORDER BY 1;
