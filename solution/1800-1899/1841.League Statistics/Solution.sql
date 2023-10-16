# Write your MySQL query statement below
WITH
    Scores AS (
        SELECT
            home_team_id AS team_id,
            CASE
                WHEN home_team_goals > away_team_goals THEN 3
                WHEN home_team_goals < away_team_goals THEN 0
                ELSE 1
            END AS score,
            home_team_goals AS goals,
            away_team_goals AS away_goals
        FROM Matches
        UNION ALL
        SELECT
            away_team_id AS team_id,
            CASE
                WHEN home_team_goals > away_team_goals THEN 0
                WHEN home_team_goals < away_team_goals THEN 3
                ELSE 1
            END AS score,
            away_team_goals AS goals,
            home_team_goals AS away_goals
        FROM Matches
    )
SELECT
    team_name,
    COUNT(1) AS matches_played,
    SUM(score) AS points,
    SUM(goals) AS goal_for,
    SUM(away_goals) AS goal_against,
    (SUM(goals) - SUM(away_goals)) AS goal_diff
FROM
    Scores AS s
    JOIN Teams AS t ON s.team_id = t.team_id
GROUP BY s.team_id
ORDER BY points DESC, goal_diff DESC, team_name;
