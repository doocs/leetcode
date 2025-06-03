# Write your MySQL query statement below
WITH
    SeasonalSales AS (
        SELECT
            CASE
                WHEN MONTH(sale_date) IN (12, 1, 2) THEN 'Winter'
                WHEN MONTH(sale_date) IN (3, 4, 5) THEN 'Spring'
                WHEN MONTH(sale_date) IN (6, 7, 8) THEN 'Summer'
                WHEN MONTH(sale_date) IN (9, 10, 11) THEN 'Fall'
            END AS season,
            category,
            SUM(quantity) AS total_quantity,
            SUM(quantity * price) AS total_revenue
        FROM
            sales
            JOIN products USING (product_id)
        GROUP BY 1, 2
    ),
    TopCategoryPerSeason AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY season
                ORDER BY total_quantity DESC, total_revenue DESC
            ) AS rk
        FROM SeasonalSales
    )
SELECT season, category, total_quantity, total_revenue
FROM TopCategoryPerSeason
WHERE rk = 1
ORDER BY 1;
