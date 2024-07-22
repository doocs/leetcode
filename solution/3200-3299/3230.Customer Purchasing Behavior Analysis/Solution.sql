# Write your MySQL query statement below
WITH
    T AS (
        SELECT *
        FROM
            Transactions
            JOIN Products USING (product_id)
    ),
    P AS (
        SELECT
            customer_id,
            category,
            COUNT(1) cnt,
            MAX(transaction_date) max_date
        FROM T
        GROUP BY 1, 2
    ),
    R AS (
        SELECT
            customer_id,
            category,
            RANK() OVER (
                PARTITION BY customer_id
                ORDER BY cnt DESC, max_date DESC
            ) rk
        FROM P
    )
SELECT
    t.customer_id,
    ROUND(SUM(amount), 2) total_amount,
    COUNT(1) transaction_count,
    COUNT(DISTINCT t.category) unique_categories,
    ROUND(AVG(amount), 2) avg_transaction_amount,
    r.category top_category,
    ROUND(COUNT(1) * 10 + SUM(amount) / 100, 2) loyalty_score
FROM
    T t
    JOIN R r ON t.customer_id = r.customer_id AND r.rk = 1
GROUP BY 1
ORDER BY 7 DESC, 1;
