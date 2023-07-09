# Write your MySQL query statement below
WITH
    S AS (
        SELECT dept_id, count(1) AS cnt
        FROM Student
        GROUP BY dept_id
    )
SELECT dept_name, ifnull(cnt, 0) AS student_number
FROM
    S
    RIGHT JOIN Department USING (dept_id)
ORDER BY 2 DESC, 1;
