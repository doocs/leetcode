# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            car_id,
            lot_id,
            SUM(TIMESTAMPDIFF(SECOND, entry_time, exit_time)) AS duration
        FROM ParkingTransactions
        GROUP BY 1, 2
    ),
    P AS (
        SELECT
            *,
            ROW_NUMBER() OVER (
                PARTITION BY car_id
                ORDER BY duration DESC
            ) AS rn
        FROM T
    ),
    C AS (
        SELECT
            car_id,
            SUM(fee_paid) AS total_fee_paid,
            SUM(TIMESTAMPDIFF(SECOND, entry_time, exit_time)) AS total_seconds
        FROM ParkingTransactions
        GROUP BY car_id
    )
SELECT
    C.car_id,
    C.total_fee_paid,
    ROUND(C.total_fee_paid / (C.total_seconds / 3600), 2) AS avg_hourly_fee,
    P.lot_id AS most_time_lot
FROM
    C
    LEFT JOIN P ON C.car_id = P.car_id AND P.rn = 1
ORDER BY C.car_id;
