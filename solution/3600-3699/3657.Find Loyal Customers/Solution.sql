# Write your MySQL query statement below
SELECT customer_id
FROM customer_transactions
GROUP BY 1
HAVING
    COUNT(1) >= 3
    AND SUM(transaction_type = 'refund') / COUNT(1) < 0.2
    AND DATEDIFF(MAX(transaction_date), MIN(transaction_date)) >= 30
ORDER BY 1;
