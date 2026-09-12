---
comments: true
difficulty: 中等
tags:
    - 数据库
---

<!-- problem:start -->

# [1070. 产品销售分析 III](https://leetcode.cn/problems/product-sales-analysis-iii)

[English Version](/solution/1000-1099/1070.Product%20Sales%20Analysis%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>销售表&nbsp;<code>Sales</code>：</p>

<pre>
+-------------+-------+
| Column Name | Type  |
+-------------+-------+
| sale_id     | int   |
| product_id  | int   |
| year        | int   |
| quantity    | int   |
| price       | int   |
+-------------+-------+
(sale_id, year) 是这张表的主键（具有唯一值的列的组合）。
这张表的每一行都表示：编号 product_id 的产品在某一年的销售额。
一个产品可能在同一年内有多个销售条目。
请注意，价格是按每单位计的。
</pre>

<p>编写解决方案，选出每个售出过的产品&nbsp;<strong>第一年</strong> 销售的 <strong>产品 id</strong>、<strong>年份</strong>、<strong>数量&nbsp;</strong>和 <strong>价格</strong>。</p>

<ul>
	<li>对每个 <code>product_id</code>，找到其在Sales表中首次出现的最早年份。</li>
	<li>返回该产品在该年度的 <strong>所有</strong> 销售条目。</li>
</ul>

<p>返回一张有这些列的表：<strong>product_id</strong>，<strong>first_year</strong>，<strong>quantity&nbsp;</strong>和<strong>&nbsp;price</strong>。</p>

<p>结果表中的条目可以按 <strong>任意顺序</strong> 排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Sales 表：
+---------+------------+------+----------+-------+
| sale_id | product_id | year | quantity | price |
+---------+------------+------+----------+-------+ 
| 1       | 100        | 2008 | 10       | 5000  |
| 2       | 100        | 2009 | 12       | 5000  |
| 7       | 200        | 2011 | 15       | 9000  |
+---------+------------+------+----------+-------+
<strong>输出：</strong>
+------------+------------+----------+-------+
| product_id | first_year | quantity | price |
+------------+------------+----------+-------+ 
| 100        | 2008       | 10       | 5000  |
| 200        | 2011       | 15       | 9000  |
+------------+------------+----------+-------+</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 每个产品只要其最早年份的全部销售行。先按产品求出最小年份，再回表取出该年的数量与价格。
>
> 子查询 `GROUP BY product_id` 得到 `(product_id, MIN(year))`，外层用元组 `IN` 过滤 `Sales`。
>
> 同一产品同一年可能有多行，因此不能只取一行聚合结果，而应保留所有匹配行。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    product_id,
    year AS first_year,
    quantity,
    price
FROM Sales
WHERE
    (product_id, year) IN (
        SELECT
            product_id,
            MIN(year) AS year
        FROM Sales
        GROUP BY product_id
    );
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 相关子查询对每个产品再聚合一次。窗口函数可以在同一趟扫描里标出组内年份名次。
>
> `RANK() OVER (PARTITION BY product_id ORDER BY year)` 把最早年份标为 $1$，外层留下 `rk = 1` 的行，并列最早的多行都会保留。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY product_id
                ORDER BY year
            ) AS rk
        FROM Sales
    )
SELECT product_id, year AS first_year, quantity, price
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
