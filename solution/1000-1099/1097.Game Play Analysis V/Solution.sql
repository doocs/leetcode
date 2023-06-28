# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            player_id,
            event_date,
            min(event_date) OVER (PARTITION BY player_id) AS install_dt
        FROM Activity
    )
SELECT
    install_dt,
    count(DISTINCT player_id) AS installs,
    round(
        sum(if(datediff(event_date, install_dt) = 1, 1, 0)) / count(
            DISTINCT player_id
        ),
        2
    ) AS day1_retention
FROM t
GROUP BY install_dt;
