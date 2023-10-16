# Write your MySQL query statement below
SELECT
    stock_name,
    SUM(IF(operation = 'Buy', -price, price)) AS capital_gain_loss
FROM Stocks
GROUP BY 1;
