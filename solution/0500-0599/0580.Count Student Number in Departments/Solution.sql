SELECT dept_name,
        ifnull(total,
        0) AS student_number
FROM department
LEFT JOIN
    (SELECT dept_id,
         count(*) AS total
    FROM student
    GROUP BY  dept_id) tmp
    ON department.dept_id = tmp.dept_id
ORDER BY  tmp.total desc
