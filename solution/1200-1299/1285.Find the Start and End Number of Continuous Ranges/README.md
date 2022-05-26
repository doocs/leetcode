# [1285. 找到连续区间的开始和结束数字](https://leetcode.cn/problems/find-the-start-and-end-number-of-continuous-ranges)

[English Version](/solution/1200-1299/1285.Find%20the%20Start%20and%20End%20Number%20of%20Continuous%20Ranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Logs</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| log_id        | int     |
+---------------+---------+
id 是上表的主键。
上表的每一行包含日志表中的一个 ID。
</pre>

<p>&nbsp;</p>

<p>后来一些 ID 从&nbsp;<code>Logs</code>&nbsp;表中删除。编写一个 SQL 查询得到&nbsp;<code>Logs</code>&nbsp;表中的连续区间的开始数字和结束数字。</p>

<p>将查询表按照 <code>start_id</code>&nbsp;排序。</p>

<p>查询结果格式如下面的例子。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Logs 表：
+------------+
| log_id     |
+------------+
| 1          |
| 2          |
| 3          |
| 7          |
| 8          |
| 10         |
+------------+
<strong>输出：</strong>
+------------+--------------+
| start_id   | end_id       |
+------------+--------------+
| 1          | 3            |
| 7          | 8            |
| 10         | 10           |
+------------+--------------+
<strong>解释：</strong>
结果表应包含 Logs 表中的所有区间。
从 1 到 3 在表中。
从 4 到 6 不在表中。
从 7 到 8 在表中。
9 不在表中。
10 在表中。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT
  MIN(log_id) AS start_id,
  MAX(log_id) AS end_id
FROM (SELECT
  log_id,
  log_id - ROW_NUMBER() OVER (ORDER BY log_id) AS rk
FROM Logs) t
GROUP BY rk;
```

<!-- tabs:end -->
