---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1211.Queries%20Quality%20and%20Percentage/README.md
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

我们将查询结果按照 `query_name` 进行分组，然后利用 `AVG` 和 `ROUND` 函数计算 `quality` 和 `poor_query_percentage`。

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

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    IFNULL(query_name, 'null') AS query_name,
    ROUND(AVG(CAST(rating AS DECIMAL) / position), 2) AS quality,
    ROUND(
        (
            SUM(
                CASE
                    WHEN rating < 3 THEN 1
                    ELSE 0
                END
            ) / NULLIF(COUNT(*), 0)
        ) * 100,
        2
    ) AS poor_query_percentage
FROM Queries
GROUP BY query_name WITH ROLLUP
HAVING query_name IS NOT NULL;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
