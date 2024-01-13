# Write your MySQL query statement below
SELECT 'Low Salary' AS category, IFNULL(SUM(income < 20000), 0) AS accounts_count FROM Accounts
UNION
SELECT
    'Average Salary' AS category,
    IFNULL(SUM(income BETWEEN 20000 AND 50000), 0) AS accounts_count
FROM Accounts
UNION
SELECT 'High Salary' AS category, IFNULL(SUM(income > 50000), 0) AS accounts_count FROM Accounts;
