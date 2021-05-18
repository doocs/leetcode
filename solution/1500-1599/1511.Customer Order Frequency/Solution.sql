# Write your MySQL query statement below
SELECT
    c.customer_id AS CUSTOMER_ID,
    c.name AS NAME
FROM
    Customers c, Product p, Orders o
WHERE
    c.customer_id = o.customer_id
AND p.product_id = o.product_id
GROUP BY c.customer_id
HAVING sum(if(month(o.order_date)=6, price*quantity, 0)) >= 100
AND sum(if(month(o.order_date)=7, price*quantity, 0)) >= 100;