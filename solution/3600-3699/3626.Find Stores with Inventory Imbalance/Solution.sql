# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            store_id,
            product_name,
            quantity,
            RANK() OVER (
                PARTITION BY store_id
                ORDER BY price DESC, quantity DESC
            ) rk1,
            RANK() OVER (
                PARTITION BY store_id
                ORDER BY price, quantity DESC
            ) rk2,
            COUNT(1) OVER (PARTITION BY store_id) cnt
        FROM inventory
    ),
    P1 AS (
        SELECT *
        FROM T
        WHERE rk1 = 1 AND cnt >= 3
    ),
    P2 AS (
        SELECT *
        FROM T
        WHERE rk2 = 1
    )
SELECT
    s.store_id store_id,
    store_name,
    location,
    p1.product_name most_exp_product,
    p2.product_name cheapest_product,
    ROUND(p2.quantity / p1.quantity, 2) imbalance_ratio
FROM
    P1 p1
    JOIN P2 p2 ON p1.store_id = p2.store_id AND p1.quantity < p2.quantity
    JOIN stores s ON p1.store_id = s.store_id
ORDER BY imbalance_ratio DESC, store_name;
