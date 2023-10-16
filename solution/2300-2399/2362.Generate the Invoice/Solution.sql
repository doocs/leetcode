# Write your MySQL query statement below
WITH
    P AS (
        SELECT *
        FROM
            Purchases
            JOIN Products USING (product_id)
    ),
    T AS (
        SELECT invoice_id, SUM(price * quantity) AS amount
        FROM P
        GROUP BY invoice_id
        ORDER BY 2 DESC, 1
        LIMIT 1
    )
SELECT product_id, quantity, (quantity * price) AS price
FROM
    P
    JOIN T USING (invoice_id);
