# Write your MySQL query statement below
SELECT product_id, product_name, description
FROM products
WHERE description REGEXP '(?-i)\\bSN[0-9]{4}-[0-9]{4}\\b'
ORDER BY 1;
