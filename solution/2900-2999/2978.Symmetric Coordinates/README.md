# [2978. Symmetric Coordinates](https://leetcode.cn/problems/symmetric-coordinates)

[English Version](/solution/2900-2999/2978.Symmetric%20Coordinates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <font face="monospace"><code>Coordinates</code></font></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| X           | int  |
| Y           | int  |
+-------------+------+
Each row includes X and Y, where both are integers. Table may contain duplicate values.
</pre>

<p>Two coordindates <code>(X1, Y1)</code> and <code>(X2, Y2)</code> are said to be <strong>symmetric</strong> coordintes if <code>X1 == Y2</code> and <code>X2 == Y1</code>.</p>

<p>Write a solution that outputs, among all these <strong>symmetric</strong> <strong>coordintes</strong>, only those <strong>unique</strong> coordinates that satisfy the condition <code>X1 &lt;= Y1</code>.</p>

<p>Return <em>the result table ordered by </em><code>X</code> <em>and </em> <code>Y</code> <em>(respectively)</em> <em>in <strong>ascending order</strong></em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Coordinates table:
+----+----+
| X  | Y  |
+----+----+
| 20 | 20 |
| 20 | 20 |
| 20 | 21 |
| 23 | 22 |
| 22 | 23 |
| 21 | 20 |
+----+----+
<strong>Output:</strong> 
+----+----+
| x  | y  |
+----+----+
| 20 | 20 |
| 20 | 21 |
| 22 | 23 |
+----+----+
<strong>Explanation:</strong> 
- (20, 20) and (20, 20) are symmetric coordinates because, X1 == Y2 and X2 == Y1. This results in displaying (20, 20) as a distinctive coordinates.
- (20, 21) and (21, 20) are symmetric coordinates because, X1 == Y2 and X2 == Y1. However, only (20, 21) will be displayed because X1 &lt;= Y1.
- (23, 22) and (22, 23) are symmetric coordinates because, X1 == Y2 and X2 == Y1. However, only (22, 23) will be displayed because X1 &lt;= Y1.
The output table is sorted by X and Y in ascending order.
</pre>

## 解法

### 方法一：窗口函数 + 自连接

我们可以使用窗口函数 `ROW_NUMBER()` 来为每一行添加一个自增的序号，然后再自连接两张表，连接条件为 `p1.x = p2.y AND p1.y = p2.x AND p1.x <= p1.y AND p1.id != p2.id`，最后再排序去重即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    P AS (
        SELECT
            ROW_NUMBER() OVER () AS id,
            x,
            y
        FROM Coordinates
    )
SELECT DISTINCT
    p1.x,
    p1.y
FROM
    P AS p1
    JOIN P AS p2 ON p1.x = p2.y AND p1.y = p2.x AND p1.x <= p1.y AND p1.id != p2.id
ORDER BY 1, 2;
```

<!-- tabs:end -->

<!-- end -->
