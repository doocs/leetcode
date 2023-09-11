# Write your MySQL query statement below
SELECT
    a.student_id,
    student_name,
    b.subject_name,
    count(c.subject_name) AS attended_exams
FROM
    Students AS a
    CROSS JOIN Subjects AS b
    LEFT JOIN Examinations AS c ON a.student_id = c.student_id AND b.subject_name = c.subject_name
GROUP BY a.student_id, b.subject_name
ORDER BY a.student_id, b.subject_name;
