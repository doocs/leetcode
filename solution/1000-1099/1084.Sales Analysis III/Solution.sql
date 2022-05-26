SELECT p.product_id,
    P.product_name
FROM product AS p
    JOIN sales AS s ON p.product_id = s.product_id
GROUP BY p.product_id
HAVING SUM(sale_date < '2019-01-01') = 0
    AND SUM(sale_date > '2019-03-31') = 0;