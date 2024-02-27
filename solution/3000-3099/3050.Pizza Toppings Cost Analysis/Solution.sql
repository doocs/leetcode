# Write your MySQL query statement below
WITH
    T AS (
        SELECT *, RANK() OVER (ORDER BY topping_name) AS rk
        FROM Toppings
    )
SELECT
    CONCAT(t1.topping_name, ',', t2.topping_name, ',', t3.topping_name) AS pizza,
    t1.cost + t2.cost + t3.cost AS total_cost
FROM
    T AS t1
    JOIN T AS t2 ON t1.rk < t2.rk
    JOIN T AS t3 ON t2.rk < t3.rk
ORDER BY 2 DESC, 1 ASC;
