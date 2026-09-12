---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3214.Year%20on%20Year%20Growth%20Rate/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3214. 同比增长率 🔒](https://leetcode.cn/problems/year-on-year-growth-rate)

[English Version](/solution/3200-3299/3214.Year%20on%20Year%20Growth%20Rate/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>user_transactions</code></p>

<pre>
+------------------+----------+
| Column Name      | Type     | 
+------------------+----------+
| transaction_id   | integer  |
| product_id       | integer  |
| spend            | decimal  |
| transaction_date | datetime |
+------------------+----------+
transaction_id 列唯一标识了表中的每一列。
这张表的每一行含有交易 ID，产品 ID，总花费以及交易日期。
</pre>

<p>编写一个解决方案来计算 <strong>每个产品</strong> 总支出的 <strong>同比增长率</strong>。</p>

<p>结果表应该包含以下列：</p>

<ul>
	<li><code>year</code>：交易的年份。</li>
	<li><code>product_id</code>：产品的 ID。</li>
	<li><code>curr_year_spend</code>：当年的总支出。</li>
	<li><code>prev_year_spend</code>：上一年的总支出。</li>
	<li><code>yoy_rate</code>：同比增速百分比，四舍五入至小数点后 2 位。</li>
</ul>

<p>返回结果表以&nbsp;<code>product_id</code>，<code>year</code>&nbsp;<strong>升序</strong> 排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p><code>user_transactions</code> 表：</p>

<pre class="example-io">
+----------------+------------+---------+---------------------+
| transaction_id | product_id | spend   | transaction_date    |
+----------------+------------+---------+---------------------+
| 1341           | 123424     | 1500.60 | 2019-12-31 12:00:00 |
| 1423           | 123424     | 1000.20 | 2020-12-31 12:00:00 |
| 1623           | 123424     | 1246.44 | 2021-12-31 12:00:00 |
| 1322           | 123424     | 2145.32 | 2022-12-31 12:00:00 |
+----------------+------------+---------+---------------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+------+------------+----------------+----------------+----------+
| year | product_id | curr_year_spend| prev_year_spend| yoy_rate |
+------+------------+----------------+----------------+----------+
| 2019 | 123424     | 1500.60        | NULL           | NULL     |
| 2020 | 123424     | 1000.20        | 1500.60        | -33.35   |
| 2021 | 123424     | 1246.44        | 1000.20        | 24.62    |
| 2022 | 123424     | 2145.32        | 1246.44        | 72.12    |
+------+------------+----------------+----------------+----------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于产品 ID 123424:
	<ul>
		<li>在 2019：
		<ul>
			<li>当年的支出是 1500.60</li>
			<li>没有上一年支出的记录</li>
			<li>同比增长率：NULL</li>
		</ul>
		</li>
		<li>在 2020：
		<ul>
			<li>当年的支出是 1000.20</li>
			<li>上一年的支出是 1500.60</li>
			<li>同比增长率：((1000.20 - 1500.60) / 1500.60) * 100 = -33.35%</li>
		</ul>
		</li>
		<li>在 2021：
		<ul>
			<li>当年的支出是 1246.44</li>
			<li>上一年的支出是 1000.20</li>
			<li>同比增长率：((1246.44 - 1000.20) / 1000.20) * 100 = 24.62%</li>
		</ul>
		</li>
		<li>在 2022：
		<ul>
			<li>当年的支出是 2145.32</li>
			<li>上一年的支出是 1246.44</li>
			<li>同比增长率：((2145.32 - 1246.44) / 1246.44) * 100 = 72.12%</li>
		</ul>
		</li>
	</ul>
	</li>
</ul>

<p><strong>注意：</strong>输出表以&nbsp;<code>product_id</code> 和&nbsp;<code>year</code>&nbsp;升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计 + 左连接

<!-- thinking:start -->

> **思考**
>
> 需要按商品与年份汇总花费，再与上一年对齐计算同比。逐行用应用层循环分组亦可，但集合运算更直接。
>
> 先按 `product_id` 与 `YEAR(transaction_date)` 聚合得到当年花费，再自左连接「同一商品且年份差 $1$」的行，用 $(\textit{curr}-\textit{prev})/\textit{prev}$ 得到增长率。无上一年的行自然留下空值。

<!-- thinking:end -->

我们可以先按照 `product_id` 和 `year(transaction_date)` 进行分组统计，然后使用左连接将当前年份的统计结果与上一年份的统计结果进行关联，最后计算年同比增长率。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT product_id, YEAR(transaction_date) year, SUM(spend) curr_year_spend
        FROM user_transactions
        GROUP BY 1, 2
    ),
    S AS (
        SELECT t1.year, t1.product_id, t1.curr_year_spend, t2.curr_year_spend prev_year_spend
        FROM
            T t1
            LEFT JOIN T t2 ON t1.product_id = t2.product_id AND t1.year = t2.year + 1
    )
SELECT
    *,
    ROUND((curr_year_spend - prev_year_spend) / prev_year_spend * 100, 2) yoy_rate
FROM S
ORDER BY 2, 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
