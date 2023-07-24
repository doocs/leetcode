# Write your MySQL query statement below
SELECT
    flight_id,
    least(count(passenger_id), capacity) AS booked_cnt,
    greatest(count(passenger_id) - capacity, 0) AS waitlist_cnt
FROM
    Flights
    LEFT JOIN Passengers USING (flight_id)
GROUP BY 1
ORDER BY 1;
