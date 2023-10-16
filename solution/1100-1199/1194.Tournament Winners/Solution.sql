# Write your MySQL query statement below
WITH
    s AS (
        SELECT first_player AS player_id, first_score AS score, group_id
        FROM
            Matches AS m
            JOIN Players AS p ON m.first_player = p.player_id
        UNION ALL
        SELECT second_player AS player_id, second_score AS score, group_id
        FROM
            Matches AS m
            JOIN Players AS p ON m.second_player = p.player_id
    ),
    t AS (
        SELECT group_id, player_id, SUM(score) AS scores
        FROM s
        GROUP BY player_id
    ),
    p AS (
        SELECT
            group_id,
            player_id,
            RANK() OVER (
                PARTITION BY group_id
                ORDER BY scores DESC, player_id
            ) AS rk
        FROM t
    )
SELECT group_id, player_id
FROM p
WHERE rk = 1;
