# Write your MySQL query statement below
SELECT
    item_category AS category,
    SUM(IF(DAYOFWEEK(order_date) = '2', quantity, 0)) AS Monday,
    SUM(IF(DAYOFWEEK(order_date) = '3', quantity, 0)) AS Tuesday,
    SUM(IF(DAYOFWEEK(order_date) = '4', quantity, 0)) AS Wednesday,
    SUM(IF(DAYOFWEEK(order_date) = '5', quantity, 0)) AS Thursday,
    SUM(IF(DAYOFWEEK(order_date) = '6', quantity, 0)) AS Friday,
    SUM(IF(DAYOFWEEK(order_date) = '7', quantity, 0)) AS Saturday,
    SUM(IF(DAYOFWEEK(order_date) = '1', quantity, 0)) AS Sunday
FROM
    Orders AS o
    RIGHT JOIN Items AS i ON o.item_id = i.item_id
GROUP BY category
ORDER BY category;
