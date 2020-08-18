SELECT s.product_id,
        p.product_name
FROM Sales s, Product p
WHERE s.product_id = p.product_id
GROUP BY  s.product_id
HAVING sum(sale_date < '2019-01-01') = 0
        AND sum(sale_date > '2019-03-31') = 0
