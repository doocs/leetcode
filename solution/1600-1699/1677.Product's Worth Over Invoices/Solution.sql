# Write your MySQL query statement below
SELECT
    name,
    ifnull(sum(rest), 0) AS rest,
    ifnull(sum(paid), 0) AS paid,
    ifnull(sum(canceled), 0) AS canceled,
    ifnull(sum(refunded), 0) AS refunded
FROM
    Product
    LEFT JOIN Invoice USING (product_id)
GROUP BY product_id
ORDER BY name;
