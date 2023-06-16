# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            rank() OVER (
                PARTITION BY exam_id
                ORDER BY score DESC
            ) AS rk1,
            rank() OVER (
                PARTITION BY exam_id
                ORDER BY score ASC
            ) AS rk2
        FROM Exam
    )
SELECT
    t.student_id,
    student_name
FROM
    t
    JOIN Student AS s ON t.student_id = s.student_id
GROUP BY t.student_id
HAVING sum(rk1 = 1) = 0 AND sum(rk2 = 1) = 0;
