# Write your MySQL query statement below
SELECT
    CEIL(DAYOFMONTH(purchase_date) / 7) AS week_of_month,
    purchase_date,
    SUM(amount_spend) AS total_amount
FROM Purchases
WHERE DATE_FORMAT(purchase_date, '%Y%m') = '202311' AND DAYOFWEEK(purchase_date) = 6
GROUP BY 2
ORDER BY 1;
