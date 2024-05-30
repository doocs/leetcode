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
            RANK() OVER (
                PARTITION BY car_id
                ORDER BY duration DESC
            ) AS rk
        FROM T
    )
SELECT
    t1.car_id,
    SUM(fee_paid) AS total_fee_paid,
    ROUND(
        SUM(fee_paid) / (SUM(TIMESTAMPDIFF(SECOND, entry_time, exit_time)) / 3600),
        2
    ) AS avg_hourly_fee,
    t2.lot_id AS most_time_lot
FROM
    ParkingTransactions AS t1
    LEFT JOIN P AS t2 ON t1.car_id = t2.car_id AND t2.rk = 1
GROUP BY 1
ORDER BY 1;
