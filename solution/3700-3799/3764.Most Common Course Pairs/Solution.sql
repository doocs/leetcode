# Write your MySQL query statement below
WITH
    top_students AS (
        SELECT user_id
        FROM course_completions
        GROUP BY user_id
        HAVING COUNT(1) >= 5 AND AVG(course_rating) >= 4
    ),
    course_pairs AS (
        SELECT
            course_name AS first_course,
            LEAD(course_name) OVER (
                PARTITION BY user_id
                ORDER BY completion_date
            ) second_course
        FROM
            top_students
            JOIN course_completions USING (user_id)
    )
SELECT
    *,
    COUNT(1) transition_count
FROM course_pairs
WHERE second_course IS NOT NULL
GROUP BY 1, 2
ORDER BY 3 DESC, 1, 2;
