# Write your MySQL query statement below
WITH
    T AS (
        SELECT YEAR(transaction_date) year, product_id, SUM(spend) tot_spend
        FROM user_transactions
        GROUP BY 1, 2
    ),
    S AS (
        SELECT
            year,
            product_id,
            tot_spend curr_year_spend,
            LAG(tot_spend) OVER (
                PARTITION BY product_id
                ORDER BY year
            ) prev_year_spend
        FROM T
    )
SELECT
    *,
    ROUND((curr_year_spend - prev_year_spend) / prev_year_spend * 100, 2) yoy_rate
FROM S;
