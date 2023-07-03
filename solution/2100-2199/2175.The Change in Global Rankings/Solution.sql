# Write your MySQL query statement below
WITH
    P AS (
        SELECT team_id, sum(points_change) AS delta
        FROM PointsChange
        GROUP BY team_id
    )
SELECT
    team_id,
    name,
    CAST(rank() OVER (ORDER BY points DESC, name) AS SIGNED) - CAST(
        rank() OVER (ORDER BY (points + delta) DESC, name) AS SIGNED
    ) AS 'rank_diff'
FROM
    TeamPoints
    JOIN P USING (team_id);
