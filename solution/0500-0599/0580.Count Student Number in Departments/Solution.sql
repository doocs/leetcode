SELECT 
    department.dept_name, COUNT(student.dept_id) student_number
FROM
    Student
        RIGHT JOIN
    Department ON student.dept_id = department.dept_id
GROUP BY dept_name
ORDER BY student_number DESC , dept_name;
