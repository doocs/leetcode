# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            tiv_2016,
            COUNT(pid) OVER (PARTITION BY tiv_2015) AS cnt1,
            COUNT(pid) OVER (PARTITION BY CONCAT(lat, '-', lon)) AS cnt2
        FROM Insurance
    )
SELECT ROUND(IFNULL(SUM(tiv_2016), 0), 2) AS tiv_2016
FROM T
WHERE cnt1 > 1 AND cnt2 = 1;
