# Write your MySQL query statement below
SELECT sp.salesperson_id, name, IFNULL(SUM(price), 0) AS total
FROM
    Salesperson AS sp
    LEFT JOIN Customer AS c ON sp.salesperson_id = c.salesperson_id
    LEFT JOIN Sales AS s ON s.customer_id = c.customer_id
GROUP BY 1;
