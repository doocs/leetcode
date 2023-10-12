# Write your MySQL query statement below
SELECT customer_id, name
FROM
    Orders
    JOIN Product USING (product_id)
    JOIN Customers USING (customer_id)
WHERE year(order_date) = 2020
GROUP BY 1
HAVING
    sum(if(month(order_date) = 6, quantity * price, 0)) >= 100
    AND sum(if(month(order_date) = 7, quantity * price, 0)) >= 100;
