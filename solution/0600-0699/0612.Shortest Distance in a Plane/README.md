# [612. 平面上的最近距离](https://leetcode.cn/problems/shortest-distance-in-a-plane)

[English Version](/solution/0600-0699/0612.Shortest%20Distance%20in%20a%20Plane/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>Point2D</code> 表：</p>

<div class="original__bRMd">
<div>
<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| x           | int  |
| y           | int  |
+-------------+------+
(x, y) 是该表的主键列(具有唯一值的列的组合)。
这张表的每一行表示 X-Y 平面上一个点的位置
</pre>

<p>&nbsp;</p>

<p><code>p<sub>1</sub>(x<sub>1</sub>, y<sub>1</sub>)</code> 和 <code>p<sub>2</sub>(x<sub>2</sub>, y<sub>2</sub>)</code> 这两点之间的距离是 <code>sqrt((x<sub>2</sub> - x<sub>1</sub>)<sup>2</sup> + (y<sub>2</sub> - y<sub>1</sub>)<sup>2</sup>)</code> 。</p>

<p>编写解决方案，报告 <code>Point2D</code> 表中任意两点之间的最短距离。保留 <strong>2 位小数</strong> 。</p>

<p>返回结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Point2D table:
+----+----+
| x  | y  |
+----+----+
| -1 | -1 |
| 0  | 0  |
| -1 | -2 |
+----+----+
<strong>输出：</strong>
+----------+
| shortest |
+----------+
| 1.00     |
+----------+
<strong>解释：</strong>最短距离是 1.00 ，从点 (-1, -1) 到点 (-1, 2) 。
</pre>
</div>
</div>

<p>&nbsp;</p>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT ROUND(SQRT(POW(p1.x - p2.x, 2) + POW(p1.y - p2.y, 2)), 2) AS shortest
FROM
    Point2D AS p1
    JOIN Point2D AS p2 ON p1.x != p2.x OR p1.y != p2.y
ORDER BY 1
LIMIT 1;
```

<!-- tabs:end -->

<!-- end -->
