---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3230.Customer%20Purchasing%20Behavior%20Analysis/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3230. 客户购买行为分析 🔒](https://leetcode.cn/problems/customer-purchasing-behavior-analysis)

[English Version](/solution/3200-3299/3230.Customer%20Purchasing%20Behavior%20Analysis/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Transactions</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| transaction_id   | int     |
| customer_id      | int     |
| product_id       | int     |
| transaction_date | date    |
| amount           | decimal |
+------------------+---------+
transaction_id 是这张表的唯一标识符。
这张表的每一行包含一次交易的信息，包括客户 ID，产品 ID，日期和总花费。
</pre>

<p>表：<code>Products</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| category    | varchar |
| price       | decimal |
+-------------+---------+
product_id 是这张表的唯一标识符。
这张表的每一行包含一个产品的信息，包括它的分类和价格。
</pre>

<p>编写一个解决方案来分析用户购买行为。对于 <strong>每个消费者</strong>，计算：</p>

<ul>
	<li>总消费额</li>
	<li>交易数量</li>
	<li>购买的 <strong>不同</strong> 产品类别的数量。</li>
	<li>平均消费金额。</li>
	<li><strong>最常购买</strong> 的产品类别（如果相同，选择最近交易的那个）</li>
	<li><strong>忠诚度分数 </strong>定义为：(交易数量 * 10) + (总消费&nbsp;/ 100)。</li>
</ul>

<p>将&nbsp;<code>total_amount</code>，&nbsp;<code>avg_transaction_amount</code>&nbsp;和&nbsp;<code>loyalty_score</code>&nbsp;舍入到&nbsp;<code>2</code> 位小数。</p>

<p>返回结果表以&nbsp;<code>loyalty_score</code>&nbsp;<strong>降序</strong> 排序，然后以<em>&nbsp;</em><code>customer_id</code><em>&nbsp;</em><strong>升序</strong><em> </em>排序。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p><code>Transactions</code> 表：</p>

<pre class="example-io">
+----------------+-------------+------------+------------------+--------+
| transaction_id | customer_id | product_id | transaction_date | amount |
+----------------+-------------+------------+------------------+--------+
| 1              | 101         | 1          | 2023-01-01       | 100.00 |
| 2              | 101         | 2          | 2023-01-15       | 150.00 |
| 3              | 102         | 1          | 2023-01-01       | 100.00 |
| 4              | 102         | 3          | 2023-01-22       | 200.00 |
| 5              | 101         | 3          | 2023-02-10       | 200.00 |
+----------------+-------------+------------+------------------+--------+
</pre>

<p><code>Products</code> 表：</p>

<pre class="example-io">
+------------+----------+--------+
| product_id | category | price  |
+------------+----------+--------+
| 1          | A        | 100.00 |
| 2          | B        | 150.00 |
| 3          | C        | 200.00 |
+------------+----------+--------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+--------------+-------------------+-------------------+------------------------+--------------+---------------+
| customer_id | total_amount | transaction_count | unique_categories | avg_transaction_amount | top_category | loyalty_score |
+-------------+--------------+-------------------+-------------------+------------------------+--------------+---------------+
| 101         | 450.00       | 3                 | 3                 | 150.00                 | C            | 34.50         |
| 102         | 300.00       | 2                 | 2                 | 150.00                 | C            | 23.00         |
+-------------+--------------+-------------------+-------------------+------------------------+--------------+---------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于消费者 101：
	<ul>
		<li>总消费额：100.00 + 150.00 + 200.00 = 450.00</li>
		<li>交易次数：3</li>
		<li>不同分类：A, B, C （3 个分类）</li>
		<li>平均交易金额：450.00 / 3 = 150.00</li>
		<li>最高分类：C （消费者 101 在分类 A，B，C 分别进行了一次交易。因为所有分类的数量都一样，我们选择最近的那次交易，即在 2023-02-10 的分类&nbsp;C）</li>
		<li>忠诚度分数：(3 * 10) + (450.00 / 100) = 34.50</li>
	</ul>
	</li>
	<li>对于消费者 102：
	<ul>
		<li>总消费额：100.00 + 200.00 = 300.00</li>
		<li>交易次数：2</li>
		<li>不同分类：A, C（2 个分类）</li>
		<li>平均交易金额：300.00 / 2 = 150.00</li>
		<li>最高分类：C （消费者 102 在分类 A 和 C 分别进行了一次交易。因为所有分类的数量都一样，我们选择最近的那次交易，即在 2023-01-22 的分类&nbsp;C）</li>
		<li>忠诚度分数：(2 * 10) + (300.00 / 100) = 23.00</li>
	</ul>
	</li>
</ul>

<p><strong>注意：</strong>输出表以&nbsp;loyalty_score 降序排序，然后以&nbsp;customer_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组 + 窗口函数 + 连接

<!-- thinking:start -->

> **思考**
>
> 要按用户汇总金额、次数、类别数，并找出「次数最多、最近一次更晚」的类别。多次分组与排序若在应用层拼表，逻辑容易散。
>
> 先连接交易与商品，按用户与类别统计次数和最近日期，再用窗口 `RANK` 取出每个用户的第一类别，最后与明细聚合忠诚度并排序。窗口保证并列规则与题意一致。

<!-- thinking:end -->

我们首先将 `Transactions` 表和 `Products` 表连接起来，记录在临时表 `T` 中。

然后，我们使用 `T` 表计算每个用户在每个类别下的交易次数以及最近的交易日期，将结果保存在临时表 `P` 中。

接着，我们使用 `P` 表计算每个用户在每个类别下的交易次数的排名，将结果保存在临时表 `R` 中。

最后，我们使用 `T` 表和 `R` 表计算每个用户的总交易金额、交易次数、唯一类别数、平均交易金额、最常购买的类别、忠诚度分数，并按照忠诚度分数降序、用户 ID 升序的顺序返回结果。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT *
        FROM
            Transactions
            JOIN Products USING (product_id)
    ),
    P AS (
        SELECT
            customer_id,
            category,
            COUNT(1) cnt,
            MAX(transaction_date) max_date
        FROM T
        GROUP BY 1, 2
    ),
    R AS (
        SELECT
            customer_id,
            category,
            RANK() OVER (
                PARTITION BY customer_id
                ORDER BY cnt DESC, max_date DESC
            ) rk
        FROM P
    )
SELECT
    t.customer_id,
    ROUND(SUM(amount), 2) total_amount,
    COUNT(1) transaction_count,
    COUNT(DISTINCT t.category) unique_categories,
    ROUND(AVG(amount), 2) avg_transaction_amount,
    r.category top_category,
    ROUND(COUNT(1) * 10 + SUM(amount) / 100, 2) loyalty_score
FROM
    T t
    JOIN R r ON t.customer_id = r.customer_id AND r.rk = 1
GROUP BY 1
ORDER BY 7 DESC, 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
