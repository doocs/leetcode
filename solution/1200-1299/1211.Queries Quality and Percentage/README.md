---
comments: true
difficulty: 简单
tags:
    - 数据库
---

<!-- problem:start -->

# [1211. 查询结果的质量和占比](https://leetcode.cn/problems/queries-quality-and-percentage)

[English Version](/solution/1200-1299/1211.Queries%20Quality%20and%20Percentage/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>Queries</code>&nbsp;表：&nbsp;</p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| query_name  | varchar |
| result      | varchar |
| position    | int     |
| rating      | int     |
+-------------+---------+
此表可能有重复的行。
此表包含了一些从数据库中收集的查询信息。
“位置”（<code>position</code>）列的值为 <strong>1</strong> 到 <strong>500</strong> 。
“评分”（<code>rating</code>）列的值为 <strong>1</strong> 到 <strong>5</strong> 。评分小于 3 的查询被定义为质量很差的查询。
</pre>

<p>&nbsp;</p>

<p>将查询结果的质量 <code>quality</code> 定义为：</p>

<blockquote>
<p>各查询结果的评分与其位置之间比率的平均值。</p>
</blockquote>

<p>将劣质查询百分比&nbsp;<code>poor_query_percentage</code>&nbsp;定义为：</p>

<blockquote>
<p>评分小于 3 的查询结果占全部查询结果的百分比。</p>
</blockquote>

<p>编写解决方案，找出每次的&nbsp;<code>query_name</code>&nbsp;、&nbsp;<code>quality</code>&nbsp;和&nbsp;<code>poor_query_percentage</code>。</p>

<p><code>quality</code>&nbsp;和&nbsp;<code>poor_query_percentage</code>&nbsp;都应 <strong>四舍五入到小数点后两位</strong> 。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Queries table:
+------------+-------------------+----------+--------+
| query_name | result            | position | rating |
+------------+-------------------+----------+--------+
| Dog        | Golden Retriever  | 1        | 5      |
| Dog        | German Shepherd   | 2        | 5      |
| Dog        | Mule              | 200      | 1      |
| Cat        | Shirazi           | 5        | 2      |
| Cat        | Siamese           | 3        | 3      |
| Cat        | Sphynx            | 7        | 4      |
+------------+-------------------+----------+--------+
<strong>输出：</strong>
+------------+---------+-----------------------+
| query_name | quality | poor_query_percentage |
+------------+---------+-----------------------+
| Dog        | 2.50    | 33.33                 |
| Cat        | 0.66    | 33.33                 |
+------------+---------+-----------------------+
<strong>解释：</strong>
Dog 查询结果的质量为 ((5 / 1) + (5 / 2) + (1 / 200)) / 3 = 2.50
Dog 查询结果的劣质查询百分比为 (1 / 3) * 100 = 33.33

Cat 查询结果的质量为 ((2 / 5) + (3 / 3) + (4 / 7)) / 3 = 0.66
Cat 查询结果的劣质查询百分比为 (1 / 3) * 100 = 33.33
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计

<!-- thinking:start -->

> **思考**
>
> 质量是 $rating/position$ 的均值，劣质占比是 $rating < 3$ 的比例。二者都是按查询名聚合的标量，直接 $GROUP\ BY\ query\_name$，用 $AVG$ 分别计算比值与布尔均值，再 $ROUND$ 到两位。空查询名按题意排除。

<!-- thinking:end -->

按 `query_name` 分组，用 `AVG(rating / position)` 计算 `quality`，用 `AVG(rating < 3)` 计算劣质查询占比，再以 `ROUND` 保留两位小数。`WHERE query_name IS NOT NULL` 用来去掉空查询名。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    query_name,
    ROUND(AVG(rating / position), 2) AS quality,
    ROUND(AVG(rating < 3) * 100, 2) AS poor_query_percentage
FROM Queries
WHERE query_name IS NOT NULL
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：分组统计（CASE 表达式）

<!-- thinking:start -->

> **思考**
>
> 部分环境下布尔参与 $AVG$ 的语义不直观。方法二用 $CAST$ 保证除法为小数，并用 $CASE$ 对劣质行计数再除以总行数，结果与方法一相同，表达更易移植。

<!-- thinking:end -->

同样按 `query_name` 分组并去掉空查询名。`quality` 用 `CAST` 做小数除法；劣质查询占比改用 `CASE` 计数再除以总行数。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    query_name,
    ROUND(AVG(CAST(rating AS DECIMAL) / position), 2) AS quality,
    ROUND(
        SUM(
            CASE
                WHEN rating < 3 THEN 1
                ELSE 0
            END
        ) / COUNT(*) * 100,
        2
    ) AS poor_query_percentage
FROM Queries
WHERE query_name IS NOT NULL
GROUP BY query_name;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
