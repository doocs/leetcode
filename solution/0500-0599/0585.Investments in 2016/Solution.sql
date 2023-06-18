# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            tiv_2016,
            count(pid) OVER (PARTITION BY tiv_2015) AS cnt1,
            count(pid) OVER (PARTITION BY concat(lat, lon)) AS cnt2
        FROM Insurance
    )
SELECT
    round(sum(TIV_2016), 2) AS tiv_2016
FROM t
WHERE cnt1 != 1 AND cnt2 = 1;
