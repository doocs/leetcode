# Write your MySQL query statement below
WITH
    S AS (
        SELECT *, row_number() OVER () AS rk
        FROM CoffeeShop
    ),
    T AS (
        SELECT
            *,
            sum(
                CASE
                    WHEN drink IS NULL THEN 0
                    ELSE 1
                END
            ) OVER (ORDER BY rk) AS gid
        FROM S
    )
SELECT
    id,
    max(drink) OVER (
        PARTITION BY gid
        ORDER BY rk
    ) AS drink
FROM T;
