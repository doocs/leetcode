# Write your MySQL query statement below
SELECT
    t.team_id,
    t.team_name,
    SUM(
        CASE
            WHEN t.team_id = m.host_team
            AND m.host_goals > m.guest_goals THEN 3
            WHEN m.host_goals = m.guest_goals THEN 1
            WHEN t.team_id = m.guest_team
            AND m.guest_goals > m.host_goals THEN 3
            ELSE 0
        END
    ) AS num_points
FROM
    Teams AS t
    LEFT JOIN Matches AS m ON t.team_id = m.host_team OR t.team_id = m.guest_team
GROUP BY t.team_id
ORDER BY num_points DESC, team_id;
