# Write your MySQL query statement below
SELECT
    team_id,
    team_name,
    wins * 3 + draws points,
    RANK() OVER (ORDER BY (wins * 3 + draws) DESC) position
FROM TeamStats
ORDER BY 3 DESC, 2;
