SELECT customer_id
FROM Customer
GROUP BY customer_id
HAVING COUNT(DISTINCT product_key) = (
	SELECT COUNT(*)
	FROM Product
)
