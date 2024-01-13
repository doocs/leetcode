# Write your MySQL query statement below
SELECT seller_id
FROM Sales
GROUP BY seller_id
HAVING
    SUM(price) >= ALL (
        SELECT SUM(price)
        FROM Sales
        GROUP BY seller_id
    );
