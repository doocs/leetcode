SELECT seller_id
FROM Sales
GROUP BY  seller_id
HAVING sum(price) =
    (SELECT sum(price) AS s
    FROM Sales
    GROUP BY  seller_id
    ORDER BY  s DESC limit 1)
