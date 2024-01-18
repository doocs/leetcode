# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            SUM(cnt) OVER (ORDER BY dt, bus_id) AS cur,
            IF(@t > 0, @t := cnt, @t := @t + cnt) AS cur_sum
        FROM
            (
                SELECT bus_id, arrival_time AS dt, capacity AS cnt FROM Buses
                UNION ALL
                SELECT -1, arrival_time AS dt, -1 FROM Passengers
            ) AS a
            JOIN (SELECT @t := 0 AS x) AS b
    )
SELECT
    bus_id,
    IF(cur_sum > 0, cnt - cur_sum, cnt) AS passengers_cnt
FROM T
WHERE bus_id > 0
ORDER BY bus_id;
