# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            DATE_FORMAT(pay_date, '%Y-%m') AS pay_month,
            department_id,
            AVG(amount) OVER (PARTITION BY pay_date) AS company_avg_amount,
            AVG(amount) OVER (PARTITION BY pay_date, department_id) AS department_avg_amount
        FROM
            Salary AS s
            JOIN Employee AS e ON s.employee_id = e.employee_id
    )
SELECT DISTINCT
    pay_month,
    department_id,
    CASE
        WHEN company_avg_amount = department_avg_amount THEN 'same'
        WHEN company_avg_amount < department_avg_amount THEN 'higher'
        ELSE 'lower'
    END AS comparison
FROM t;
