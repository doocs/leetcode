# Write your MySQL query statement below
SELECT product_name, year, price
FROM
    Sales
    JOIN Product USING (product_id);
