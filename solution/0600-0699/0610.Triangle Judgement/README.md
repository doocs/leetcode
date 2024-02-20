# [610. 判断三角形](https://leetcode.cn/problems/triangle-judgement)

[English Version](/solution/0600-0699/0610.Triangle%20Judgement/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表:&nbsp;<code>Triangle</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| x           | int  |
| y           | int  |
| z           | int  |
+-------------+------+
在 SQL 中，(x, y, z)是该表的主键列。
该表的每一行包含三个线段的长度。
</pre>

<p>&nbsp;</p>

<p>对每三个线段报告它们是否可以形成一个三角形。</p>

<p>以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Triangle 表:
+----+----+----+
| x  | y  | z  |
+----+----+----+
| 13 | 15 | 30 |
| 10 | 20 | 15 |
+----+----+----+
<strong>输出:</strong> 
+----+----+----+----------+
| x  | y  | z  | triangle |
+----+----+----+----------+
| 13 | 15 | 30 | No       |
| 10 | 20 | 15 | Yes      |
+----+----+----+----------+</pre>

## 解法

### 方法一：IF 语句 + 三角形判断条件

三条边能否构成三角形的条件是：任意两边之和大于第三边。因此，我们可以使用 `IF` 语句来判断是否满足这个条件，如果满足则返回 `Yes`，否则返回 `No`。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    *,
    IF(x + y > z AND x + z > y AND y + z > x, 'Yes', 'No') AS triangle
FROM Triangle;
```

<!-- tabs:end -->

<!-- end -->
