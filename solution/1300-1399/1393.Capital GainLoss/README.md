# [1393. 股票的资本损益](https://leetcode.cn/problems/capital-gainloss)

[English Version](/solution/1300-1399/1393.Capital%20GainLoss/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>Stocks</code>&nbsp;表：</p>

<pre>+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| stock_name    | varchar |
| operation     | enum    |
| operation_day | int     |
| price         | int     |
+---------------+---------+
(stock_name, day) 是这张表的主键
operation 列使用的是一种枚举类型，包括：(&#39;Sell&#39;,&#39;Buy&#39;)
此表的每一行代表了名为 stock_name 的某支股票在 operation_day 这一天的操作价格。
保证股票的每次&#39;Sell&#39;操作前，都有相应的&#39;Buy&#39;操作。
</pre>

<p>&nbsp;</p>

<p>编写一个SQL查询来报告每支股票的资本损益。</p>

<p>股票的资本损益是一次或多次买卖股票后的全部收益或损失。</p>

<p>以任意顺序返回结果即可。</p>

<p>SQL查询结果的格式如下例所示：</p>

<pre><code>Stocks</code> 表:
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

Result 表:
+---------------+-------------------+
| stock_name    | capital_gain_loss |
+---------------+-------------------+
| Corona Masks  | 9500              |
| Leetcode      | 8000              |
| Handbags      | -23000            |
+---------------+-------------------+
Leetcode 股票在第一天以1000美元的价格买入，在第五天以9000美元的价格卖出。资本收益=9000-1000=8000美元。
Handbags 股票在第17天以30000美元的价格买入，在第29天以7000美元的价格卖出。资本损失=7000-30000=-23000美元。
Corona Masks 股票在第1天以10美元的价格买入，在第3天以1010美元的价格卖出。在第4天以1000美元的价格再次购买，在第5天以500美元的价格出售。最后，它在第6天以1000美元的价格被买走，在第10天以10000美元的价格被卖掉。资本损益是每次（&rsquo;Buy&#39;-&gt;&#39;Sell&#39;）操作资本收益或损失的和=（1010-10）+（500-1000）+（10000-1000）=1000-500+9000=9500美元。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

`CASE WHEN` + `GROUP BY`。

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
