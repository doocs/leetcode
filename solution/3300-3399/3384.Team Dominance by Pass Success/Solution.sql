# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            t1.team_name,
            IF(time_stamp <= '45:00', 1, 2) half_number,
            IF(t1.team_name = t2.team_name, 1, -1) dominance
        FROM
            Passes p
            JOIN Teams t1 ON p.pass_from = t1.player_id
            JOIN Teams t2 ON p.pass_to = t2.player_id
    )
SELECT team_name, half_number, SUM(dominance) dominance
FROM T
GROUP BY 1, 2
ORDER BY 1, 2;
