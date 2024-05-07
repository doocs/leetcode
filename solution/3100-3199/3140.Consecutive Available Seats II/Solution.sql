# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            seat_id - (RANK() OVER (ORDER BY seat_id)) AS gid
        FROM Cinema
        WHERE free = 1
    ),
    P AS (
        SELECT
            MIN(seat_id) AS first_seat_id,
            MAX(seat_id) AS last_seat_id,
            COUNT(1) AS consecutive_seats_len
        FROM T
        GROUP BY gid
    ),
    S AS (
        SELECT
            *,
            RANK() OVER (ORDER BY consecutive_seats_len DESC) AS rk
        FROM P
    )
SELECT first_seat_id, last_seat_id, consecutive_seats_len
FROM S
WHERE rk = 1
ORDER BY 1;
