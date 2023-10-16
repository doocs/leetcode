# Write your MySQL query statement below
SELECT
    s.company_id,
    employee_id,
    employee_name,
    ROUND(
        CASE
            WHEN top < 1000 THEN salary
            WHEN top >= 1000
            AND top <= 10000 THEN salary * 0.76
            ELSE salary * 0.51
        END
    ) AS salary
FROM
    Salaries AS s
    JOIN (
        SELECT company_id, MAX(salary) AS top
        FROM Salaries
        GROUP BY company_id
    ) AS t
        ON s.company_id = t.company_id;
