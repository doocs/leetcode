# Write your MySQL query statement below
WITH
    S AS (
        SELECT *
        FROM
            Salary
            JOIN Employee USING (employee_id)
    ),
    T AS (
        SELECT
            DATE_FORMAT(pay_date, '%Y-%m') AS pay_month,
            department_id,
            AVG(amount) OVER (PARTITION BY pay_date, department_id) AS department_avg,
            AVG(amount) OVER (PARTITION BY pay_date) AS company_avg
        FROM S
    )
SELECT
    pay_month,
    department_id,
    CASE
        WHEN AVG(department_avg) > AVG(company_avg) THEN 'higher'
        WHEN AVG(department_avg) < AVG(company_avg) THEN 'lower'
        ELSE 'same'
    END AS comparison
FROM T
GROUP BY 1, 2;
