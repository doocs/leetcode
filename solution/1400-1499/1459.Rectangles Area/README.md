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
id 是该表主键
每个点都用二维坐标 (x_value, y_value) 表示</pre>

<p> </p>

<p>写一个 SQL 语句，报告由表中任意两点可以形成的所有<strong> 边与坐标轴平行 </strong>且 <strong>面积不为零</strong> 的矩形。</p>

<p>结果表中的每一行包含三列 <code>(p1, p2, area)</code> 如下:</p>

<ul>
	<li><code>p1</code> 和 <code>p2</code> 是矩形两个对角的 <code>id</code></li>
	<li>矩形的面积由列 <code>area</code><strong> </strong>表示</li>
</ul>

<p>请按照面积 <code>area</code> 大小降序排列；如果面积相同的话, 则按照 <code>p1</code> 升序排序；若仍相同，则按 <code>p2</code> 升序排列。</p>

<p>查询结果如下例所示：</p>

<pre>
Points 表:
+----------+-------------+-------------+
| id       | x_value     | y_value     |
+----------+-------------+-------------+
| 1        | 2           | 7           |
| 2        | 4           | 8           |
| 3        | 2           | 10          |
+----------+-------------+-------------+

Result 表:
+----------+-------------+-------------+
| p1       | p2          | area        |
+----------+-------------+-------------+
| 2        | 3           | 4           |
| 1        | 2           | 2           |
+----------+-------------+-------------+

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1459.Rectangles%20Area/images/rect.png" style="width: 200px; height: 330px;" />

p1 = 2 且 p2 = 3 时, 面积等于 |4-2| * |8-10| = 4
p1 = 1 且 p2 = 2 时, 面积等于 ||2-4| * |7-8| = 2 
p1 = 1 且 p2 = 3 时, 是不可能为矩形的, 面积等于 0
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
