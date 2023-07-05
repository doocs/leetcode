# Write your MySQL query statement below
WITH
    S AS (
        SELECT
            hall_id,
            start_day,
            end_day,
            max(end_day) OVER (
                PARTITION BY hall_id
                ORDER BY start_day
            ) AS cur_max_end_day
        FROM HallEvents
    ),
    T AS (
        SELECT
            *,
            if(
                start_day <= lag(cur_max_end_day) OVER (
                    PARTITION BY hall_id
                    ORDER BY start_day
                ),
                0,
                1
            ) AS start
        FROM S
    ),
    P AS (
        SELECT
            *,
            sum(start) OVER (
                PARTITION BY hall_id
                ORDER BY start_day
            ) AS gid
        FROM T
    )
SELECT hall_id, min(start_day) AS start_day, max(end_day) AS end_day
FROM P
GROUP BY hall_id, gid;
