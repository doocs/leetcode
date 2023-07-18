# Write your MySQL query statement below
SELECT
    stock_name,
    sum(if(operation = 'Buy', -price, price)) AS capital_gain_loss
FROM Stocks
GROUP BY 1;
