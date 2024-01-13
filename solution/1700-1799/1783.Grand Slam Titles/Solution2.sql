# Write your MySQL query statement below
SELECT
    player_id,
    player_name,
    SUM(
        (
            CASE
                WHEN Wimbledon = player_id THEN 1
                ELSE 0
            END
        ) + (
            CASE
                WHEN Fr_open = player_id THEN 1
                ELSE 0
            END
        ) + (
            CASE
                WHEN US_open = player_id THEN 1
                ELSE 0
            END
        ) + (
            CASE
                WHEN Au_open = player_id THEN 1
                ELSE 0
            END
        )
    ) AS grand_slams_count
FROM
    Championships
    CROSS JOIN Players
GROUP BY player_id
HAVING grand_slams_count > 0;
