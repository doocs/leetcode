# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            tiv_2016,
            COUNT(1) OVER (PARTITION BY tiv_2015) AS cnt1,
            COUNT(1) OVER (PARTITION BY lat, lon) AS cnt2
        FROM Insurance
    )
SELECT ROUND(SUM(tiv_2016), 2) AS tiv_2016
FROM T
WHERE cnt1 > 1 AND cnt2 = 1;
