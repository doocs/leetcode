# Write your MySQL query statement below
SELECT s.name
FROM
    SalesPerson AS s
    LEFT JOIN Orders USING (sales_id)
    LEFT JOIN Company AS c USING (com_id)
GROUP BY sales_id
HAVING ifnull(sum(c.name = 'RED'), 0) = 0;
