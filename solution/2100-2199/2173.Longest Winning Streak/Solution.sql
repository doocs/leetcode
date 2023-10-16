# Write your MySQL query statement below
WITH
    S AS (
        SELECT
            *,
            ROW_NUMBER() OVER (
                PARTITION BY player_id
                ORDER BY match_day
            ) - ROW_NUMBER() OVER (
                PARTITION BY player_id, result
                ORDER BY match_day
            ) AS rk
        FROM Matches
    ),
    T AS (
        SELECT player_id, SUM(result = 'Win') AS s
        FROM S
        GROUP BY player_id, rk
    )
SELECT player_id, MAX(s) AS longest_streak
FROM T
GROUP BY player_id;
