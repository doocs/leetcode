# Write your MySQL query statement below
WITH
    T AS (
        SELECT product_id, YEAR(transaction_date) year, SUM(spend) curr_year_spend
        FROM user_transactions
        GROUP BY 1, 2
    ),
    S AS (
        SELECT t1.year, t1.product_id, t1.curr_year_spend, t2.curr_year_spend prev_year_spend
        FROM
            T t1
            LEFT JOIN T t2 ON t1.product_id = t2.product_id AND t1.year = t2.year + 1
    )
SELECT
    *,
    ROUND((curr_year_spend - prev_year_spend) / prev_year_spend * 100, 2) yoy_rate
FROM S
ORDER BY 2, 1;
