# Write your MySQL query statement below
WITH
    T AS (
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
        sum(datediff(event_date, install_dt) = 1) / count(DISTINCT player_id),
        2
    ) AS day1_retention
FROM T
GROUP BY 1;
