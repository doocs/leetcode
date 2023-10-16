# Write your MySQL query statement below
WITH
    T AS (
        SELECT Wimbledon AS player_id
        FROM Championships
        UNION ALL
        SELECT Fr_open AS player_id
        FROM Championships
        UNION ALL
        SELECT US_open AS player_id
        FROM Championships
        UNION ALL
        SELECT Au_open AS player_id
        FROM Championships
    )
SELECT player_id, player_name, COUNT(1) AS grand_slams_count
FROM
    T
    JOIN Players USING (player_id)
GROUP BY 1;
