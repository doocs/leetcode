# Write your MySQL query statement below
SELECT  buyer_id
FROM
(
	SELECT  buyer_id
	       ,CASE WHEN p.product_name = 'S8' THEN 1  ELSE 0 END     AS s8
	       ,CASE WHEN p.product_name = 'iPhone' THEN 1  ELSE 0 END AS iPhone
	FROM Product p
	JOIN Sales s
	ON p.product_id = s.product_id
) t
GROUP BY  buyer_id
HAVING SUM(S8) > 0 AND SUM(iPhone) = 0;