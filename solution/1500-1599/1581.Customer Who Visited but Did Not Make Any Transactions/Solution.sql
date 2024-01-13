# Write your MySQL query statement below
SELECT customer_id, COUNT(1) AS count_no_trans
FROM Visits
WHERE visit_id NOT IN (SELECT visit_id FROM Transactions)
GROUP BY 1;
