SELECT s.buyer_id
FROM Sales s
JOIN Product p
    ON s.product_id = p.product_id
GROUP BY  s.buyer_id
HAVING sum(p.product_name='S8')>0
        AND sum(p.product_name='iPhone')=0
