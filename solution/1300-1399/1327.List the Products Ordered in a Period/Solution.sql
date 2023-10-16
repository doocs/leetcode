# Write your MySQL query statement below
SELECT product_name, SUM(unit) AS unit
FROM
    Orders AS o
    JOIN Products AS p ON o.product_id = p.product_id
WHERE DATE_FORMAT(order_date, '%Y-%m') = '2020-02'
GROUP BY o.product_id
HAVING unit >= 100;
