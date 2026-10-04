# Write your MySQL query statement below
WITH
    ranked_sessions AS (
        SELECT
            ss.student_id,
            ss.session_id,
            ss.session_date,
            ss.subject,
            ss.hours_studied,
            DATEDIFF(
                ss.session_date,
                LAG(ss.session_date) OVER (
                    PARTITION BY ss.student_id
                    ORDER BY ss.session_date, ss.session_id
                )
            ) AS date_diff,
            ROW_NUMBER() OVER (
                PARTITION BY ss.student_id
                ORDER BY ss.session_date, ss.session_id
            ) AS session_index
        FROM study_sessions ss
    ),
    contiguous_students AS (
        SELECT student_id
        FROM ranked_sessions
        GROUP BY student_id
        HAVING
            SUM(
                CASE
                    WHEN date_diff > 2 THEN 1
                    ELSE 0
                END
            ) = 0
    ),
    student_stats AS (
        SELECT
            r.student_id,
            COUNT(*) AS session_count,
            COUNT(DISTINCT r.subject) AS cycle_length,
            SUM(r.hours_studied) AS total_hours
        FROM
            ranked_sessions r
            JOIN contiguous_students c ON c.student_id = r.student_id
        GROUP BY r.student_id
        HAVING
            cycle_length >= 3
            AND session_count >= cycle_length * 2
            AND session_count % cycle_length = 0
    )
SELECT
    s.student_id,
    s.student_name,
    s.major,
    st.cycle_length,
    st.total_hours AS total_study_hours
FROM
    student_stats st
    JOIN students s ON s.student_id = st.student_id
WHERE
    NOT EXISTS (
        SELECT 1
        FROM
            ranked_sessions cur
            JOIN ranked_sessions cyc
                ON cyc.student_id = cur.student_id
                AND cyc.session_index = (cur.session_index - 1) % st.cycle_length + 1
        WHERE cur.student_id = st.student_id AND NOT (cur.subject <=> cyc.subject)
    )
ORDER BY st.cycle_length DESC, st.total_hours DESC;
