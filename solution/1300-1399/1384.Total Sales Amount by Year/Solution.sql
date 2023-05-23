# Write your MySQL query statement below
SELECT
    s.product_id,
    p.product_name,
    y.YEAR report_year,
    s.average_daily_sales * (
        IF (
            YEAR (s.period_end) > y.YEAR,
            y.days_of_year,
            dayofyear(s.period_end)
        ) - IF (
            YEAR (s.period_start) < y.YEAR,
            1,
            dayofyear(s.period_start)
        ) + 1
    ) total_amount
FROM
    Sales s
    INNER JOIN (
        SELECT
            '2018' YEAR,
            365 days_of_year
        UNION
        ALL
        SELECT
            '2019' YEAR,
            365 days_of_year
        UNION
        ALL
        SELECT
            '2020' YEAR,
            366 days_of_year
    ) y ON YEAR (s.period_start) <= y.YEAR
    AND YEAR (s.period_end) >= y.YEAR
    INNER JOIN Product p ON p.product_id = s.product_id
ORDER BY
    s.product_id,
    y.YEAR