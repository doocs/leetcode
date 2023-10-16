# Write your MySQL query statement below
SELECT
    student_id,
    department_id,
    IFNULL(
        ROUND(
            (
                RANK() OVER (
                    PARTITION BY department_id
                    ORDER BY mark DESC
                ) - 1
            ) * 100 / (COUNT(1) OVER (PARTITION BY department_id) - 1),
            2
        ),
        0
    ) AS percentage
FROM Students;
