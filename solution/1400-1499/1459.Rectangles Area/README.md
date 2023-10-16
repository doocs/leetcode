# [1459. 矩形面积](https://leetcode.cn/problems/rectangles-area)

[English Version](/solution/1400-1499/1459.Rectangles%20Area/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Points</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| x_value       | int     |
| y_value       | int     |
+---------------+---------+
id 是该表中具有唯一值的列。
每个点都用二维坐标 (x_value, y_value) 表示。</pre>

<p>&nbsp;</p>

<p>编写解决方案，报告由表中任意两点可以形成的所有<strong> 边与坐标轴平行 </strong>且 <strong>面积不为零</strong> 的矩形。</p>

<p>结果表中的每一行包含三列 <code>(p1, p2, area)</code>&nbsp;如下:</p>

<ul>
	<li><code>p1</code>&nbsp;和&nbsp;<code>p2</code>&nbsp;是矩形两个对角的 <code>id</code></li>
	<li>矩形的面积由列&nbsp;<code>area</code><strong>&nbsp;</strong>表示</li>
</ul>

<p>返回结果表请按照面积&nbsp;<code>area</code> 大小 <strong>降序排列</strong>；如果面积相同的话, 则按照&nbsp;<code>p1</code>&nbsp;<strong>升序排序</strong>；若仍相同，则按 <code>p2</code> <strong>升序排列</strong>。</p>

<p>返回结果格式如下例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1459.Rectangles%20Area/images/rect.png" style="width: 200px; height: 330px;" /></p>

<pre>
<strong>输入：</strong>
Points 表:
+----------+-------------+-------------+
| id       | x_value     | y_value     |
+----------+-------------+-------------+
| 1        | 2           | 7           |
| 2        | 4           | 8           |
| 3        | 2           | 10          |
+----------+-------------+-------------+
<strong>输出：</strong>
+----------+-------------+-------------+
| p1       | p2          | area        |
+----------+-------------+-------------+
| 2        | 3           | 4           |
| 1        | 2           | 2           |
+----------+-------------+-------------+
<strong>解释：</strong>
p1 = 2 且 p2 = 3 时, 面积等于 |4-2| * |8-10| = 4
p1 = 1 且 p2 = 2 时, 面积等于 ||2-4| * |7-8| = 2 
p1 = 1 且 p2 = 3 时, 是不可能为矩形的, 面积等于 0
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    p1.id AS p1,
    p2.id AS p2,
    ABS(p1.x_value - p2.x_value) * ABS(p1.y_value - p2.y_value) AS area
FROM
    Points AS p1
    JOIN Points AS p2 ON p1.id < p2.id
WHERE p1.x_value != p2.x_value AND p1.y_value != p2.y_value
ORDER BY area DESC, p1, p2;
```

<!-- tabs:end -->
