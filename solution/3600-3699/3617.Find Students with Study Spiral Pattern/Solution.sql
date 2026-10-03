# Write your MySQL query statement below
WITH RECURSIVE
    ranked_sessions AS (
        SELECT
            ss.student_id,
            ss.session_date,
            ss.subject,
            ss.hours_studied,
            ROW_NUMBER() OVER (
                PARTITION BY ss.student_id
                ORDER BY ss.session_date
            ) AS session_rank
        FROM study_sessions ss
    ),
    grouped_sessions AS (
        SELECT
            *,
            DATEDIFF(
                session_date,
                LAG(session_date) OVER (
                    PARTITION BY student_id
                    ORDER BY session_date
                )
            ) AS date_diff
        FROM ranked_sessions
    ),
    session_groups AS (
        SELECT
            *,
            SUM(
                CASE
                    WHEN date_diff > 2 OR date_diff IS NULL THEN 1
                    ELSE 0
                END
            ) OVER (
                PARTITION BY student_id
                ORDER BY session_date
            ) AS group_id
        FROM grouped_sessions
    ),
    numbered_sessions AS (
        SELECT
            *,
            ROW_NUMBER() OVER (
                PARTITION BY student_id, group_id
                ORDER BY session_date
            ) AS session_index,
            COUNT(*) OVER (
                PARTITION BY student_id, group_id
            ) AS session_count,
            SUM(hours_studied) OVER (
                PARTITION BY student_id, group_id
            ) AS total_hours
        FROM session_groups
    ),
    cycle_candidates AS (
        SELECT
            student_id,
            group_id,
            session_count,
            total_hours,
            3 AS cycle_length
        FROM numbered_sessions
        WHERE session_index = 1 AND session_count >= 6

        UNION ALL

        SELECT
            student_id,
            group_id,
            session_count,
            total_hours,
            cycle_length + 1
        FROM cycle_candidates
        WHERE cycle_length < FLOOR(session_count / 2)
    ),
    matching_patterns AS (
        SELECT
            c.student_id,
            c.group_id,
            c.cycle_length,
            c.total_hours
        FROM cycle_candidates c
        WHERE MOD(c.session_count, c.cycle_length) = 0
            AND (
                SELECT COUNT(DISTINCT cycle_subject.subject)
                FROM numbered_sessions cycle_subject
                WHERE cycle_subject.student_id = c.student_id
                    AND cycle_subject.group_id = c.group_id
                    AND cycle_subject.session_index <= c.cycle_length
            ) >= 3
            AND NOT EXISTS (
                SELECT 1
                FROM numbered_sessions current_session
                JOIN numbered_sessions cycle_session
                    ON cycle_session.student_id = current_session.student_id
                    AND cycle_session.group_id = current_session.group_id
                    AND cycle_session.session_index =
                        MOD(current_session.session_index - 1, c.cycle_length) + 1
                WHERE current_session.student_id = c.student_id
                    AND current_session.group_id = c.group_id
                    AND NOT (current_session.subject <=> cycle_session.subject)
            )
    ),
    ranked_patterns AS (
        SELECT
            student_id,
            group_id,
            cycle_length,
            total_hours,
            ROW_NUMBER() OVER (
                PARTITION BY student_id, group_id
                ORDER BY cycle_length
            ) AS pattern_rank
        FROM matching_patterns
    )
SELECT
    s.student_id,
    s.student_name,
    s.major,
    p.cycle_length,
    p.total_hours AS total_study_hours
FROM ranked_patterns p
JOIN students s ON s.student_id = p.student_id
WHERE p.pattern_rank = 1
ORDER BY p.cycle_length DESC, p.total_hours DESC;
