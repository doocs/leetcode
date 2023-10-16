# Write your MySQL query statement below
WITH
    T AS (
        SELECT * FROM Flights
        UNION
        SELECT arrival_airport, departure_airport, flights_count FROM Flights
    ),
    P AS (
        SELECT departure_airport, SUM(flights_count) AS cnt
        FROM T
        GROUP BY 1
    )
SELECT departure_airport AS airport_id
FROM P
WHERE cnt = (SELECT MAX(cnt) FROM P);
