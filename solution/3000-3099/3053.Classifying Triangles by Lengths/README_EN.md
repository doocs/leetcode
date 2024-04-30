# [3053. Classifying Triangles by Lengths 🔒](https://leetcode.com/problems/classifying-triangles-by-lengths)

[中文文档](/solution/3000-3099/3053.Classifying%20Triangles%20by%20Lengths/README.md)

<!-- tags:Database -->

## Description

<p>Table: <font face="monospace"><code>Triangles</code></font></p>

<pre>
+-------------+------+ 
| Column Name | Type | 
+-------------+------+ 
| A           | int  | 
| B           | int  |
| C           | int  |
+-------------+------+
(A, B, C) is the primary key for this table.
Each row include the lengths of each of a triangle&#39;s three sides.
</pre>

<p>Write a query to find the type of <strong>triangle</strong>. Output one of the following for each row:</p>

<ul>
	<li><strong>Equilateral</strong>: It&#39;s a triangle with <code>3</code> sides of equal length.</li>
	<li><strong>Isosceles</strong>: It&#39;s a triangle with <code>2</code> sides of equal length.</li>
	<li><strong>Scalene</strong>: It&#39;s a triangle with <code>3</code> sides of differing lengths.</li>
	<li><strong>Not A Triangle: </strong>The given values of <code>A</code>, <code>B</code>, and <code>C</code> don&#39;t form a triangle.</li>
</ul>

<p>Return <em>the result table in <strong>any order</strong></em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Triangles table:
+----+----+----+
| A  | B  | C  |
+----+----+----+
| 20 | 20 | 23 |
| 20 | 20 | 20 |
| 20 | 21 | 22 |
| 13 | 14 | 30 |
+----+----+----+
<strong>Output:</strong> 
+----------------+
| triangle_type  | 
+----------------+
| Isosceles      | 
| Equilateral    |
| Scalene        |
| Not A Triangle |
+----------------+
<strong>Explanation:</strong> 
- Values in the first row from an Isosceles triangle, because A = B.
- Values in the second row from an Equilateral triangle, because A = B = C.
- Values in the third row from an Scalene triangle, because A != B != C.
- Values in the fourth row cannot form a triangle, because the combined value of sides A and B is not larger than that of side C.</pre>

## Solutions

### Solution 1: Using CASE WHEN Statement

We can use the `CASE WHEN` statement to determine the type of the triangle.

First, we need to determine whether the three sides can form a triangle. If not, we return `Not A Triangle`.

Then, we check if the lengths of the three sides are equal. If they are, we return `Equilateral`.

Next, we check if there are two sides with equal length. If there are, we return `Isosceles`.

Otherwise, it means that the lengths of the three sides are all different, so we return `Scalene`.

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
