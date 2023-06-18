# Write your MySQL query statement below
SELECT
    product_name,
    o.product_id,
    o.order_id,
    o.order_date
FROM
    Orders AS o
    JOIN Products AS p ON o.product_id = p.product_id
WHERE
    (o.product_id, order_date) IN (
        SELECT
            product_id,
            max(order_date) AS order_date
        FROM Orders
        GROUP BY product_id
    )
ORDER BY product_name, o.product_id, o.order_id;
