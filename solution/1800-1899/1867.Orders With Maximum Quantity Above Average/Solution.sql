# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            order_id,
            max(quantity) AS max_quantity,
            sum(quantity) / count(1) AS avg_quantity
        FROM OrdersDetails
        GROUP BY order_id
    )
SELECT order_id
FROM t
WHERE max_quantity > (SELECT max(avg_quantity) FROM t);
