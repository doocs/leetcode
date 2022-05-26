SELECT customer_number
FROM
    (SELECT customer_number,
         count(*) AS total
    FROM orders
    GROUP BY  customer_number
    ORDER BY  total DESC limit 1) tmp
