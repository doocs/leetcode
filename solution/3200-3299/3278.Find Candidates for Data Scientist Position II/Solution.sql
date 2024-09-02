WITH
    S AS (
        SELECT
            candidate_id,
            project_id,
            COUNT(*) matched_skills,
            SUM(
                CASE
                    WHEN proficiency > importance THEN 10
                    WHEN proficiency < importance THEN -5
                    ELSE 0
                END
            ) + 100 AS score
        FROM
            Candidates
            JOIN Projects USING (skill)
        GROUP BY 1, 2
    ),
    T AS (
        SELECT project_id, COUNT(1) required_skills
        FROM Projects
        GROUP BY 1
    ),
    P AS (
        SELECT
            project_id,
            candidate_id,
            score,
            RANK() OVER (
                PARTITION BY project_id
                ORDER BY score DESC, candidate_id
            ) rk
        FROM
            S
            JOIN T USING (project_id)
        WHERE matched_skills = required_skills
    )
SELECT project_id, candidate_id, score
FROM P
WHERE rk = 1
ORDER BY 1;
