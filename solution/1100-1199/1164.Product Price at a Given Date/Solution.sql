# Write your MySQL query statement below
SELECT
    p1.product_id AS product_id,
    ifnull(p2.price, 10) AS price
FROM
    (
        SELECT DISTINCT
            (product_id) AS product_id
        FROM Products
    ) AS p1
    LEFT JOIN (
        SELECT
            t1.product_id,
            t1.new_price AS price
        FROM
            Products AS t1
            JOIN (
                SELECT
                    product_id,
                    max(change_date) AS change_date
                FROM Products
                WHERE change_date <= '2019-08-16'
                GROUP BY product_id
            ) AS t2
                ON t1.product_id = t2.product_id
                AND t1.change_date = t2.change_date
    ) AS p2
        ON p1.product_id = p2.product_id;
