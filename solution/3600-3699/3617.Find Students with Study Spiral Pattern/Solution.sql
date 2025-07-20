# Write your MySQL query statement below
WITH
    ranked_sessions AS (
        SELECT
            s.student_id,
            ss.session_date,
            ss.subject,
            ss.hours_studied,
            ROW_NUMBER() OVER (
                PARTITION BY s.student_id
                ORDER BY ss.session_date
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
                    WHEN date_diff > 2
                    OR date_diff IS NULL THEN 1
                    ELSE 0
                END
            ) OVER (
                PARTITION BY student_id
                ORDER BY session_date
            ) AS group_id
        FROM grouped_sessions
    ),
    valid_sequences AS (
        SELECT
            student_id,
            group_id,
            COUNT(*) AS session_count,
            GROUP_CONCAT(subject ORDER BY session_date) AS subject_sequence,
            SUM(hours_studied) AS total_hours
        FROM session_groups
        GROUP BY student_id, group_id
        HAVING session_count >= 6
    ),
    pattern_detected AS (
        SELECT
            vs.student_id,
            vs.total_hours,
            vs.subject_sequence,
            COUNT(
                DISTINCT
                SUBSTRING_INDEX(SUBSTRING_INDEX(subject_sequence, ',', n), ',', -1)
            ) AS cycle_length
        FROM
            valid_sequences vs
            JOIN (
                SELECT a.N + b.N * 10 + 1 AS n
                FROM
                    (
                        SELECT 0 AS N
                        UNION
                        SELECT 1
                        UNION
                        SELECT 2
                        UNION
                        SELECT 3
                        UNION
                        SELECT 4
                        UNION
                        SELECT 5
                        UNION
                        SELECT 6
                        UNION
                        SELECT 7
                        UNION
                        SELECT 8
                        UNION
                        SELECT 9
                    ) a,
                    (
                        SELECT 0 AS N
                        UNION
                        SELECT 1
                        UNION
                        SELECT 2
                        UNION
                        SELECT 3
                        UNION
                        SELECT 4
                        UNION
                        SELECT 5
                        UNION
                        SELECT 6
                        UNION
                        SELECT 7
                        UNION
                        SELECT 8
                        UNION
                        SELECT 9
                    ) b
            ) nums
                ON n <= 10
        WHERE
            -- Check if the sequence repeats every `k` items, for some `k >= 3` and divides session_count exactly
            -- We simplify by checking the start and middle halves are equal
            LENGTH(subject_sequence) > 0
            AND LOCATE(',', subject_sequence) > 0
            AND (
                -- For cycle length 3:
                subject_sequence LIKE CONCAT(
                    SUBSTRING_INDEX(subject_sequence, ',', 3),
                    ',',
                    SUBSTRING_INDEX(SUBSTRING_INDEX(subject_sequence, ',', 6), ',', -3),
                    '%'
                )
                OR subject_sequence LIKE CONCAT(
                    -- For cycle length 4:
                    SUBSTRING_INDEX(subject_sequence, ',', 4),
                    ',',
                    SUBSTRING_INDEX(SUBSTRING_INDEX(subject_sequence, ',', 8), ',', -4),
                    '%'
                )
            )
        GROUP BY vs.student_id, vs.total_hours, vs.subject_sequence
    ),
    final_output AS (
        SELECT
            s.student_id,
            s.student_name,
            s.major,
            pd.cycle_length,
            pd.total_hours AS total_study_hours
        FROM
            pattern_detected pd
            JOIN students s ON s.student_id = pd.student_id
        WHERE pd.cycle_length >= 3
    )
SELECT *
FROM final_output
ORDER BY cycle_length DESC, total_study_hours DESC;
