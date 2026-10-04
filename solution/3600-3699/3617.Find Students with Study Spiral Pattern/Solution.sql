# Write your MySQL query statement below
WITH
    ranked_sessions AS (
        SELECT
            s.student_id,
            ss.session_id,
            ss.session_date,
            ss.subject,
            ss.hours_studied,
            ROW_NUMBER() OVER (
                PARTITION BY s.student_id
                ORDER BY ss.session_date, ss.session_id
            ) AS rn
        FROM
            study_sessions ss
            JOIN students s ON s.student_id = ss.student_id
    ),
    grouped_sessions AS (
        SELECT
            *,
            DATEDIFF(
                session_date,
                LAG(session_date) OVER (
                    PARTITION BY student_id
                    ORDER BY session_date, session_id
                )
            ) AS date_diff
        FROM ranked_sessions
    ),
    session_groups AS (
        SELECT
            *,
            SUM(
                CASE
                    WHEN date_diff > 2
                    OR date_diff IS NULL THEN 1
                    ELSE 0
                END
            ) OVER (
                PARTITION BY student_id
                ORDER BY session_date, session_id
            ) AS group_id
        FROM grouped_sessions
    ),
    group_stats AS (
        SELECT
            student_id,
            group_id,
            COUNT(*) AS session_count,
            COUNT(DISTINCT subject) AS cycle_length,
            SUM(hours_studied) AS total_hours
        FROM session_groups
        GROUP BY student_id, group_id
        HAVING
            cycle_length >= 3
            AND session_count >= cycle_length * 2
            AND session_count % cycle_length = 0
    ),
    numbered_sessions AS (
        SELECT
            g.student_id,
            g.group_id,
            g.subject,
            gs.cycle_length,
            gs.total_hours,
            ROW_NUMBER() OVER (
                PARTITION BY g.student_id, g.group_id
                ORDER BY g.session_date, g.session_id
            ) AS session_index
        FROM
            session_groups g
            JOIN group_stats gs ON gs.student_id = g.student_id AND gs.group_id = g.group_id
    )
SELECT
    s.student_id,
    s.student_name,
    s.major,
    n.cycle_length,
    n.total_hours AS total_study_hours
FROM
    numbered_sessions n
    JOIN students s ON s.student_id = n.student_id
WHERE
    n.session_index = 1
    AND NOT EXISTS (
        SELECT 1
        FROM
            numbered_sessions cur
            JOIN numbered_sessions cyc
                ON cyc.student_id = cur.student_id
                AND cyc.group_id = cur.group_id
                AND cyc.session_index = (cur.session_index - 1) % n.cycle_length + 1
        WHERE
            cur.student_id = n.student_id
            AND cur.group_id = n.group_id
            AND NOT (cur.subject <=> cyc.subject)
    )
ORDER BY n.cycle_length DESC, n.total_hours DESC;
