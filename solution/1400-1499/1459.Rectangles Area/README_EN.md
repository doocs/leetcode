# [1459. Rectangles Area](https://leetcode.com/problems/rectangles-area)

[中文文档](/solution/1400-1499/1459.Rectangles%20Area/README.md)

## Description

<p>Table: <code>Points</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| x_value       | int     |
| y_value       | int     |
+---------------+---------+
id is the primary key for this table.
Each point is represented as a 2D coordinate (x_value, y_value).</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report all possible <strong>axis-aligned</strong> rectangles with a <strong>non-zero area</strong> that can be formed by any two points from the <code>Points</code> table.</p>

<p>Each row in the result should contain three columns <code>(p1, p2, area)</code> where:</p>

<ul>
	<li><code>p1</code> and <code>p2</code> are the <code>id</code>&#39;s of the two points that determine the opposite corners of a rectangle.</li>
	<li><code>area</code> is the area of the rectangle and must be <strong>non-zero</strong>.</li>
</ul>

<p>Return the result table <strong>ordered</strong> by <code>area</code> <strong>in descending order</strong>. If there is a tie, order them by <code>p1</code> <strong>in ascending order</strong>. If there is still a tie, order them by <code>p2</code> <strong>in ascending order</strong>.</p>

<p>The query result format is in the following table.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1459.Rectangles%20Area/images/rect.png" style="width: 303px; height: 500px;" />
<pre>
<strong>Input:</strong> 
Points table:
+----------+-------------+-------------+
| id       | x_value     | y_value     |
+----------+-------------+-------------+
| 1        | 2           | 7           |
| 2        | 4           | 8           |
| 3        | 2           | 10          |
+----------+-------------+-------------+
<strong>Output:</strong> 
+----------+-------------+-------------+
| p1       | p2          | area        |
+----------+-------------+-------------+
| 2        | 3           | 4           |
| 1        | 2           | 2           |
+----------+-------------+-------------+
<strong>Explanation:</strong> 
The rectangle formed by p1 = 2 and p2 = 3 has an area equal to |4-2| * |8-10| = 4.
The rectangle formed by p1 = 1 and p2 = 2 has an area equal to |2-4| * |7-8| = 2.
Note that the rectangle formed by p1 = 1 and p2 = 3 is invalid because the area is 0.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
