# Write your MySQL query statement below
WITH
teams AS (
    SELECT DISTINCT team_name
    FROM Teams
),
halves AS (
    SELECT 1 AS half_number
    UNION ALL
    SELECT 2
),
T AS (
    SELECT
        t1.team_name,
        IF(p.time_stamp <= '45:00', 1, 2) AS half_number,
        IF(t1.team_name = t2.team_name, 1, -1) AS dominance
    FROM Passes p
    JOIN Teams t1 ON p.pass_from = t1.player_id
    JOIN Teams t2 ON p.pass_to = t2.player_id
),
s AS (
    SELECT
        team_name,
        half_number,
        SUM(dominance) AS dominance
    FROM T
    GROUP BY team_name, half_number
)
SELECT
    teams.team_name,
    halves.half_number,
    COALESCE(s.dominance, 0) AS dominance
FROM teams
CROSS JOIN halves
LEFT JOIN s
    ON teams.team_name = s.team_name
   AND halves.half_number = s.half_number
ORDER BY teams.team_name, halves.half_number;
