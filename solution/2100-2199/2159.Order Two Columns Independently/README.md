# [2159. 分别排序两列 🔒](https://leetcode.cn/problems/order-two-columns-independently)

[English Version](/solution/2100-2199/2159.Order%20Two%20Columns%20Independently/README_EN.md)

<!-- tags:数据库 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一

<!-- tabs:start -->

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

<!-- end -->
