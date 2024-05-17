---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1445.Apples%20%26%20Oranges/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1445. 苹果和桔子 🔒](https://leetcode.cn/problems/apples-oranges)

[English Version](/solution/1400-1499/1445.Apples%20%26%20Oranges/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Sales</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| sale_date     | date    |
| fruit         | enum    | 
| sold_num      | int     | 
+---------------+---------+
(sale_date, fruit) 是该表主键(具有唯一值的列的组合)。
该表包含了每一天中"苹果" 和 "桔子"的销售情况。
</pre>

<p>&nbsp;</p>

<p>编写解决方案报告每一天&nbsp;<strong>苹果</strong>&nbsp;和&nbsp;<strong>桔子</strong>&nbsp;销售的数目的差异.</p>

<p>返回的结果表,&nbsp;按照格式为&nbsp;('YYYY-MM-DD') 的 <code>sale_date</code> 排序.</p>

<p>返回结果表如下例所示:</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<code><strong>输入：</strong>
Sales</code> 表:
+------------+------------+-------------+
| sale_date  | fruit      | sold_num    |
+------------+------------+-------------+
| 2020-05-01 | apples     | 10          |
| 2020-05-01 | oranges    | 8           |
| 2020-05-02 | apples     | 15          |
| 2020-05-02 | oranges    | 15          |
| 2020-05-03 | apples     | 20          |
| 2020-05-03 | oranges    | 0           |
| 2020-05-04 | apples     | 15          |
| 2020-05-04 | oranges    | 16          |
+------------+------------+-------------+
<strong>输出：</strong>
+------------+--------------+
| sale_date  | diff         |
+------------+--------------+
| 2020-05-01 | 2            |
| 2020-05-02 | 0            |
| 2020-05-03 | 20           |
| 2020-05-04 | -1           |
+------------+--------------+
<strong>解释：</strong>
在 2020-05-01, 卖了 10 个苹果 和 8 个桔子 (差异为 10 - 8 = 2).
在 2020-05-02, 卖了 15 个苹果 和 15 个桔子 (差异为 15 - 15 = 0).
在 2020-05-03, 卖了 20 个苹果 和 0 个桔子 (差异为 20 - 0 = 20).
在 2020-05-04, 卖了 15 个苹果 和 16 个桔子 (差异为 15 - 16 = -1).
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组求和

我们可以将数据按照日期分组，然后用 `sum` 函数求出每天苹果和桔子 🔒 的销售差异。如果是苹果，我们就用正数表示，如果是桔子，我们就用负数表示。最后我们按照日期排序即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    sale_date,
    SUM(IF(fruit = 'apples', sold_num, -sold_num)) AS diff
FROM Sales
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
