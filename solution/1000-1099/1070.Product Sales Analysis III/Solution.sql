SELECT s.product_id,
         s.year AS first_year,
         s.quantity,
         s.price
FROM Sales s
WHERE (product_id, year) IN
    (SELECT product_id,
         min(year)
    FROM Sales
    GROUP BY  product_id)
