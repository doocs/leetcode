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
            date_format(pay_date, '%Y-%m') AS pay_month,
            department_id,
            avg(amount) OVER (PARTITION BY pay_date, department_id) AS department_avg,
            avg(amount) OVER (PARTITION BY pay_date) AS company_avg
        FROM S
    )
SELECT
    pay_month,
    department_id,
    CASE
        WHEN avg(department_avg) > avg(company_avg) THEN 'higher'
        WHEN avg(department_avg) < avg(company_avg) THEN 'lower'
        ELSE 'same'
    END AS comparison
FROM T
GROUP BY 1, 2;
