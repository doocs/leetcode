---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2159.Order%20Two%20Columns%20Independently/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2159. 分别排序两列 🔒](https://leetcode.cn/problems/order-two-columns-independently)

[English Version](/solution/2100-2199/2159.Order%20Two%20Columns%20Independently/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Data</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| first_col   | int  |
| second_col  | int  |
+-------------+------+
该表可能包含重复数据。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，使：</p>

<ul>
	<li><code>first_col</code> 按照<strong> 升序 </strong>排列。</li>
	<li><code>second_col</code> 按照 <strong>降序 </strong>排列。</li>
</ul>

<p>返回的结果格式如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Data 表：
+-----------+------------+
| first_col | second_col |
+-----------+------------+
| 4         | 2          |
| 2         | 3          |
| 3         | 1          |
| 1         | 4          |
+-----------+------------+
<strong>输出：</strong>
+-----------+------------+
| first_col | second_col |
+-----------+------------+
| 1         | 4          |
| 2         | 3          |
| 3         | 2          |
| 4         | 1          |
+-----------+------------+
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 两列需分别排序：第一列升序，第二列降序，再按行对齐。不能按原行绑定排序。
>
> 用窗口函数分别给两列打名次，再按名次连接，即可把独立排序后的值配到同一行。
>
> $\textit{first\_col}$ 升序编号，$\textit{second\_col}$ 降序编号，等值连接 $\textit{rk}$。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT
            first_col,
            ROW_NUMBER() OVER (ORDER BY first_col) AS rk
        FROM Data
    ),
    T AS (
        SELECT
            second_col,
            ROW_NUMBER() OVER (ORDER BY second_col DESC) AS rk
        FROM Data
    )
SELECT first_col, second_col
FROM
    S
    JOIN T USING (rk);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
