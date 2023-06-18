# Write your MySQL query statement below
SELECT
    name AS warehouse_name,
    SUM(units * Width * Length * Height) AS volume
FROM
    Warehouse AS w
    JOIN Products AS p ON w.product_id = p.product_id
GROUP BY name;
