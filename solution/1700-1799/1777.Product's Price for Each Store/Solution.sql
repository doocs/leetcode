# Write your MySQL query statement below
SELECT
    product_id,
    sum(if(store = 'store1', price, NULL)) AS store1,
    sum(if(store = 'store2', price, NULL)) AS store2,
    sum(if(store = 'store3', price, NULL)) AS store3
FROM Products
GROUP BY 1;
