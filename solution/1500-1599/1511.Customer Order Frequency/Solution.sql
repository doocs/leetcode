# Write your MySQL query statement below
SELECT customer_id, name
FROM
    Orders
    JOIN Product USING (product_id)
    JOIN Customers USING (customer_id)
WHERE YEAR(order_date) = 2020
GROUP BY 1
HAVING
    SUM(IF(MONTH(order_date) = 6, quantity * price, 0)) >= 100
    AND SUM(IF(MONTH(order_date) = 7, quantity * price, 0)) >= 100;
