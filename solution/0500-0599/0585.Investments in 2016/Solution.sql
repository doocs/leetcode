# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            tiv_2016,
            count(pid) OVER (PARTITION BY tiv_2015) AS cnt1,
            count(pid) OVER (PARTITION BY concat(lat, '-', lon)) AS cnt2
        FROM Insurance
    )
SELECT round(ifnull(sum(tiv_2016), 0), 2) AS tiv_2016
FROM T
WHERE cnt1 > 1 AND cnt2 = 1;
