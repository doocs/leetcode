# Write your MySQL query statement below
SELECT customer_id, COUNT(1) AS count_no_trans
FROM
    Visits
    LEFT JOIN Transactions USING (visit_id)
WHERE amount IS NULL
GROUP BY 1;
