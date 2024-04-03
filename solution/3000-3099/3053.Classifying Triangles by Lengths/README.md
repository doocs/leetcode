# [3053. 根据长度分类三角形](https://leetcode.cn/problems/classifying-triangles-by-lengths)

[English Version](/solution/3000-3099/3053.Classifying%20Triangles%20by%20Lengths/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<font face="monospace"><code>Triangles</code></font></p>

<pre>
+-------------+------+ 
| Column Name | Type | 
+-------------+------+ 
| A           | int  | 
| B           | int  |
| C           | int  |
+-------------+------+
(A, B, C) 是这张表的主键。
每一行包含三角形三边的长度。
</pre>

<p>编写一个查询来找到 <strong>三角形</strong> 的类型。对于每一行输出下面的其中一个：</p>

<ul>
	<li><strong>Equilateral</strong>：<code>3</code>&nbsp;边长度相同的三角形。</li>
	<li><strong>Isosceles</strong>：<code>2</code>&nbsp;边长度相同的三角形。</li>
	<li><strong>Scalene</strong>：<code>3</code>&nbsp;边长度不同的三角形。</li>
	<li><strong>Not A Triangle</strong>：给定的&nbsp;<code>A</code>&nbsp;，<code>B</code>，<code>C</code>&nbsp;的值不能形成三角形。</li>
</ul>

<p>以 <strong>任何顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入:</strong> 
Triangles 表：
+----+----+----+
| A  | B  | C  |
+----+----+----+
| 20 | 20 | 23 |
| 20 | 20 | 20 |
| 20 | 21 | 22 |
| 13 | 14 | 30 |
+----+----+----+
<strong>输出：</strong> 
+----------------+
| triangle_type  | 
+----------------+
| Isosceles      | 
| Equilateral    |
| Scalene        |
| Not A Triangle |
+----------------+
<strong>解释：</strong> 
- 第一行的值形成了等腰三角形，因为 A = B。
- 第二行的值形成了等边三角形，因为 A = B = C.
- 第三行的值形成了斜三角形，因为 A != B != C.
- 第四行中的值不能形成三角形，因为边 A 和边 B 的和不大于边 C。</pre>

## 解法

### 方法一：使用 CASE WHEN 语句

我们可以使用 `CASE WHEN` 语句来判断三角形的类型。

首先，我们需要判断三个边是否能够构成一个三角形。如果不能，我们返回 `Not A Triangle`。

然后，我们判断三个边的长度是否相等。如果相等，我们返回 `Equilateral`。

接着，我们判断是否有两个边的长度相等。如果有，我们返回 `Isosceles`。

否则，说明三个边的长度都不相等，我们返回 `Scalene`。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    CASE
        WHEN A + B <= C
        OR A + C <= B
        OR B + C <= A THEN 'Not A Triangle'
        WHEN A = B
        AND B = c THEN 'Equilateral'
        WHEN (A = B) + (B = C) + (A = C) = 1 THEN 'Isosceles'
        ELSE 'Scalene'
    END AS triangle_type
FROM Triangles;
```

<!-- tabs:end -->

<!-- end -->
