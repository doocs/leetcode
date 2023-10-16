# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            student_id,
            RANK() OVER (
                PARTITION BY exam_id
                ORDER BY score
            ) AS rk1,
            RANK() OVER (
                PARTITION BY exam_id
                ORDER BY score DESC
            ) AS rk2
        FROM Exam
    )
SELECT student_id, student_name
FROM
    T
    JOIN Student USING (student_id)
GROUP BY 1
HAVING SUM(rk1 = 1) = 0 AND SUM(rk2 = 1) = 0
ORDER BY 1;
