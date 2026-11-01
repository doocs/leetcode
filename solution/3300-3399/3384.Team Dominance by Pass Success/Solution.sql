# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            t1.team_name,
            IF(time_stamp <= '45:00', 1, 2) half_number,
            IF(t1.team_name = t2.team_name, 1, -1) dominance
        FROM Passes p
        JOIN Teams t1 ON p.pass_from = t1.player_id
        JOIN Teams t2 ON p.pass_to = t2.player_id
    ),
    S AS (
        SELECT team_name, half_number, SUM(dominance) dominance
        FROM T
        GROUP BY 1, 2
    )
SELECT a.team_name, b.half_number, IFNULL(S.dominance, 0) dominance
FROM (SELECT DISTINCT team_name FROM Teams) a
CROSS JOIN (
    SELECT 1 half_number
    UNION ALL
    SELECT 2
) b
LEFT JOIN S ON a.team_name = S.team_name AND b.half_number = S.half_number
ORDER BY 1, 2;
