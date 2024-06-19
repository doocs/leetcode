# Write your MySQL query statement below
WITH
    T AS (
        SELECT student_id, AVG(GPA) AS avg_gpa
        FROM enrollments
        GROUP BY 1
    )
SELECT student_id
FROM
    students
    JOIN courses USING (major)
    JOIN T USING (student_id)
    LEFT JOIN enrollments USING (student_id, course_id)
WHERE avg_gpa >= 2.5
GROUP BY 1
HAVING
    SUM(mandatory = 'yes' AND grade = 'A') = SUM(mandatory = 'yes')
    AND SUM(mandatory = 'no' AND grade IS NOT NULL) = SUM(mandatory = 'no' AND grade IN ('A', 'B'))
    AND SUM(mandatory = 'no' AND grade IS NOT NULL) >= 2
ORDER BY 1;
