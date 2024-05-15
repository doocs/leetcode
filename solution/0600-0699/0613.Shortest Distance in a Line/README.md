---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0613.Shortest%20Distance%20in%20a%20Line/README.md
tags:
    - 数据库
---

# [613. 直线上的最近距离 🔒](https://leetcode.cn/problems/shortest-distance-in-a-line)

[English Version](/solution/0600-0699/0613.Shortest%20Distance%20in%20a%20Line/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Point</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| x           | int  |
+-------------+------+
在SQL中，x是该表的主键列。
该表的每一行表示X轴上一个点的位置。
</pre>

<p>&nbsp;</p>

<p>找到 <code>Point</code> 表中任意两点之间的最短距离。</p>

<p>返回结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>
Point 表:
+----+
| x  |
+----+
| -1 |
| 0  |
| 2  |
+----+
<b>输出：</b>
+----------+
| shortest |
+----------+
| 1        |
+----------+
<b>解释：</b>点 -1 和 0 之间的最短距离为 |(-1) - 0| = 1。
</pre>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果 <code>Point</code> 表按 <strong>升序排列</strong>，如何优化你的解决方案？</p>

## 解法

### 方法一：自连接

我们可以使用自连接，将表中的每个点与其他更大的点进行连接，然后计算两点之间的距离，最后取最小值。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT MIN(p2.x - p1.x) AS shortest
FROM
    Point AS p1
    JOIN Point AS p2 ON p1.x < p2.x;
```

<!-- tabs:end -->

### 方法二：窗口函数

我们也可以使用窗口函数，将表中的点按照 $x$ 排序，然后计算相邻两点之间的距离，最后取最小值。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT x - LAG(x) OVER (ORDER BY x) AS shortest
FROM Point
ORDER BY 1
LIMIT 1, 1;
```

<!-- tabs:end -->

<!-- end -->
