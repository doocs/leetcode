SELECT 
    customer_number
FROM
    Orders
GROUP BY customer_number
ORDER BY COUNT(customer_number) DESC
LIMIT 1;
