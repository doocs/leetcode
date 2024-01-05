WITH RECURSIVE
    T AS (
        SELECT '2023-11-01' AS purchase_date
        UNION
        SELECT purchase_date + INTERVAL 1 DAY
        FROM T
        WHERE purchase_date < '2023-11-30'
    )
SELECT
    CEIL(DAYOFMONTH(purchase_date) / 7) AS week_of_month,
    purchase_date,
    IFNULL(SUM(amount_spend), 0) AS total_amount
FROM
    T
    LEFT JOIN Purchases USING (purchase_date)
WHERE DAYOFWEEK(purchase_date) = 6
GROUP BY 2
ORDER BY 1;
