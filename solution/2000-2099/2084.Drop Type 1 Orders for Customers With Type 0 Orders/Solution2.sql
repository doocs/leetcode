SELECT DISTINCT
    a.order_id,
    a.customer_id,
    a.order_type
FROM
    Orders AS a
    LEFT JOIN Orders AS b ON a.customer_id = b.customer_id AND a.order_type != b.order_type
WHERE b.order_type IS NULL OR b.order_type = 1;
