# Write your MySQL query statement below
SELECT user_id, SUM(quantity * price) AS spending
FROM
    Sales
    JOIN Product USING (product_id)
GROUP BY 1
ORDER BY 2 DESC, 1;
