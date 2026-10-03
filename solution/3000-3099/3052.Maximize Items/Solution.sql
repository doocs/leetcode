# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            IFNULL(SUM(square_footage), 0) AS s,
            COUNT(1) AS cnt
        FROM Inventory
        WHERE item_type = 'prime_eligible'
    )
SELECT
    'prime_eligible' AS item_type,
    IF(s = 0, 0, cnt * FLOOR(500000 / s)) AS item_count
FROM T
UNION ALL
SELECT
    'not_prime',
    IFNULL(COUNT(1) * FLOOR(IF(s = 0, 500000, 500000 % s) / SUM(square_footage)), 0)
FROM
    Inventory
    JOIN T
WHERE item_type = 'not_prime'
ORDER BY item_count DESC, item_type DESC;
