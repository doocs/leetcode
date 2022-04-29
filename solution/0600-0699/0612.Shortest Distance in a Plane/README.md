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
(x, y) 是这张表的主键
这张表的每一行表示 X-Y 平面上一个点的位置
</pre>

<p>&nbsp;</p>

<p><code>p<sub>1</sub>(x<sub>1</sub>, y<sub>1</sub>)</code> 和 <code>p<sub>2</sub>(x<sub>2</sub>, y<sub>2</sub>)</code> 这两点之间的距离是 <code>sqrt((x<sub>2</sub> - x<sub>1</sub>)<sup>2</sup> + (y<sub>2</sub> - y<sub>1</sub>)<sup>2</sup>)</code> 。</p>

<p>请你写一个 SQL 查询报告 <code>Point2D</code> 表中任意两点之间的最短距离。保留 <strong>2 位小数</strong> 。</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

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

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
