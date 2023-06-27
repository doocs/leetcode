# Write your MySQL query statement below
SELECT
    item_category AS category,
    sum(if(DAYOFWEEK(order_date) = '2', quantity, 0)) AS Monday,
    sum(if(DAYOFWEEK(order_date) = '3', quantity, 0)) AS Tuesday,
    sum(if(DAYOFWEEK(order_date) = '4', quantity, 0)) AS Wednesday,
    sum(if(DAYOFWEEK(order_date) = '5', quantity, 0)) AS Thursday,
    sum(if(DAYOFWEEK(order_date) = '6', quantity, 0)) AS Friday,
    sum(if(DAYOFWEEK(order_date) = '7', quantity, 0)) AS Saturday,
    sum(if(DAYOFWEEK(order_date) = '1', quantity, 0)) AS Sunday
FROM
    Orders AS o
    RIGHT JOIN Items AS i ON o.item_id = i.item_id
GROUP BY category
ORDER BY category;
