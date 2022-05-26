# [1393. Capital GainLoss](https://leetcode.com/problems/capital-gainloss)

[中文文档](/solution/1300-1399/1393.Capital%20GainLoss/README.md)

## Description

<p>Table: <code>Stocks</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| stock_name    | varchar |
| operation     | enum    |
| operation_day | int     |
| price         | int     |
+---------------+---------+
(stock_name, operation_day) is the primary key for this table.
The operation column is an ENUM of type (&#39;Sell&#39;, &#39;Buy&#39;)
Each row of this table indicates that the stock which has stock_name had an operation on the day operation_day with the price.
It is guaranteed that each &#39;Sell&#39; operation for a stock has a corresponding &#39;Buy&#39; operation in a previous day. It is also guaranteed that each &#39;Buy&#39; operation for a stock has a corresponding &#39;Sell&#39; operation in an upcoming day.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the <strong>Capital gain/loss</strong> for each stock.</p>

<p>The <strong>Capital gain/loss</strong> of a stock is the total gain or loss after buying and selling the stock one or many times.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Stocks table:
+---------------+-----------+---------------+--------+
| stock_name    | operation | operation_day | price  |
+---------------+-----------+---------------+--------+
| Leetcode      | Buy       | 1             | 1000   |
| Corona Masks  | Buy       | 2             | 10     |
| Leetcode      | Sell      | 5             | 9000   |
| Handbags      | Buy       | 17            | 30000  |
| Corona Masks  | Sell      | 3             | 1010   |
| Corona Masks  | Buy       | 4             | 1000   |
| Corona Masks  | Sell      | 5             | 500    |
| Corona Masks  | Buy       | 6             | 1000   |
| Handbags      | Sell      | 29            | 7000   |
| Corona Masks  | Sell      | 10            | 10000  |
+---------------+-----------+---------------+--------+
<strong>Output:</strong> 
+---------------+-------------------+
| stock_name    | capital_gain_loss |
+---------------+-------------------+
| Corona Masks  | 9500              |
| Leetcode      | 8000              |
| Handbags      | -23000            |
+---------------+-------------------+
<strong>Explanation:</strong> 
Leetcode stock was bought at day 1 for 1000$ and was sold at day 5 for 9000$. Capital gain = 9000 - 1000 = 8000$.
Handbags stock was bought at day 17 for 30000$ and was sold at day 29 for 7000$. Capital loss = 7000 - 30000 = -23000$.
Corona Masks stock was bought at day 1 for 10$ and was sold at day 3 for 1010$. It was bought again at day 4 for 1000$ and was sold at day 5 for 500$. At last, it was bought at day 6 for 1000$ and was sold at day 10 for 10000$. Capital gain/loss is the sum of capital gains/losses for each (&#39;Buy&#39; --&gt; &#39;Sell&#39;) operation = (1010 - 10) + (500 - 1000) + (10000 - 1000) = 1000 - 500 + 9000 = 9500$.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT stock_name,
    SUM(
        CASE
            WHEN operation = 'Buy' THEN - price
            ELSE price
        END
    ) AS capital_gain_loss
FROM Stocks
GROUP BY stock_name;
```

<!-- tabs:end -->
