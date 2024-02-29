# Write your MySQL query statement below
WITH
    T AS (
        SELECT team, AVG(workload) AS avg_workload
        FROM
            Project
            JOIN Employees USING (employee_id)
        GROUP BY 1
    )
SELECT
    employee_id,
    project_id,
    name AS employee_name,
    workload AS project_workload
FROM
    Project
    JOIN Employees USING (employee_id)
    JOIN T USING (team)
WHERE workload > avg_workload
ORDER BY 1, 2;
