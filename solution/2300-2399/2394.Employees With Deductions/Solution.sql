# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            employee_id,
            sum(ceiling(timestampdiff(second, in_time, out_time) / 60)) / 60 AS tot
        FROM Logs
        GROUP BY employee_id
    )
SELECT employee_id
FROM
    Employees
    LEFT JOIN T USING (employee_id)
WHERE ifnull(tot, 0) < needed_hours;
