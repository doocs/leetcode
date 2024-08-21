SELECT
    t1.employee_id,
    COUNT(*) / 2 AS overlapping_shifts
FROM
    EmployeeShifts t1
    JOIN EmployeeShifts t2
        ON t1.employee_id = t2.employee_id
        AND t1.start_time < t2.end_time
        AND t1.end_time > t2.start_time
        AND t1.start_time != t2.start_time
GROUP BY 1
HAVING overlapping_shifts > 0
ORDER BY 1;
