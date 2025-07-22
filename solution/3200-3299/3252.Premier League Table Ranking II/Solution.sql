WITH
    T AS (
        SELECT
            team_name,
            wins * 3 + draws AS points,
            RANK() OVER (ORDER BY wins * 3 + draws DESC) AS position,
            COUNT(1) OVER () AS total_teams
        FROM TeamStats
    )
SELECT
    team_name,
    points,
    position,
    CASE
        WHEN position <= CEIL(total_teams / 3.0) THEN 'Tier 1'
        WHEN position <= CEIL(2 * total_teams / 3.0) THEN 'Tier 2'
        ELSE 'Tier 3'
    END tier
FROM T
ORDER BY 2 DESC, 1;
