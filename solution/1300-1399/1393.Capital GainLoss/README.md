---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1393.Capital%20GainLoss/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1393. 股票的资本损益](https://leetcode.cn/problems/capital-gainloss)

[English Version](/solution/1300-1399/1393.Capital%20GainLoss/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>Stocks</code>&nbsp;表：</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| stock_name    | varchar |
| operation     | enum    |
| operation_day | int     |
| price         | int     |
+---------------+---------+
(stock_name, operation_day) 是这张表的主键(具有唯一值的列的组合)
operation 列使用的是一种枚举类型，包括：('Sell','Buy')
此表的每一行代表了名为 stock_name 的某支股票在 operation_day 这一天的操作价格。
此表可以保证，股票的每个“卖出”操作在前某一天都有相应的“买入”操作。并且，股票的每个“买入”操作在即将到来的某一天都有相应的“卖出”操作。
</pre>

<p>&nbsp;</p>

<p>编写解决方案报告每只股票的 <strong>资本损益</strong>。</p>

<p>股票的&nbsp;<strong>资本利得/损失&nbsp;</strong>是指一次或多次买卖该股票后的总收益或损失。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<code><strong>输入：</strong>
Stocks</code> 表:
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
<strong>输出：</strong>
+---------------+-------------------+
| stock_name    | capital_gain_loss |
+---------------+-------------------+
| Corona Masks  | 9500              |
| Leetcode      | 8000              |
| Handbags      | -23000            |
+---------------+-------------------+
<strong>解释：</strong>
Leetcode 股票在第一天以1000美元的价格买入，在第五天以9000美元的价格卖出。资本收益=9000-1000=8000美元。
Handbags 股票在第17天以30000美元的价格买入，在第29天以7000美元的价格卖出。资本损失=7000-30000=-23000美元。
Corona Masks 股票在第1天以10美元的价格买入，在第3天以1010美元的价格卖出。在第4天以1000美元的价格再次购买，在第5天以500美元的价格出售。最后，它在第6天以1000美元的价格被买走，在第10天以10000美元的价格被卖掉。资本损益是每次（’Buy'-&gt;'Sell'）操作资本收益或损失的和=（1010-10）+（500-1000）+（10000-1000）=1000-500+9000=9500美元。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：GROUP BY + SUM(IF())

我们使用 `GROUP BY` 将同一支股票的买卖操作分组，然后使用 `SUM(IF())` 计算每支股票的资本损益。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    stock_name,
    SUM(IF(operation = 'Buy', -price, price)) AS capital_gain_loss
FROM Stocks
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
