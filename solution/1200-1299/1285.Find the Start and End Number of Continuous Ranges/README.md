# [1285. 找到连续区间的开始和结束数字](https://leetcode.cn/problems/find-the-start-and-end-number-of-continuous-ranges)

[English Version](/solution/1200-1299/1285.Find%20the%20Start%20and%20End%20Number%20of%20Continuous%20Ranges/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Logs</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| log_id        | int     |
+---------------+---------+
id 是上表具有唯一值的列。
上表的每一行包含日志表中的一个 ID。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，得到&nbsp;<code>Logs</code>&nbsp;表中的连续区间的开始数字和结束数字。</p>

<p>返回结果表按照 <code>start_id</code>&nbsp;排序。</p>

<p>结果格式如下面的例子。</p>

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

### 方法一：分组 + 窗口函数

我们需要想办法将一段连续的日志分到同一组，然后对每一组进行聚合操作，得到每一组的开始日志和结束日志。

分组可以用以下两种方法实现：

1. 通过计算每个日志与前一个日志的差值，如果差值为 $1$，则说明这两个日志是连续的，我们设置 $delta$ 为 $0$，否则设置为 $1$。然后我们对 $delta$ 求前缀和，得到的结果就是每一行的分组的标识符。
2. 通过计算当前行的日志减去当前行的行号，得到的结果就是每一行的分组的标识符。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            log_id,
            SUM(delta) OVER (ORDER BY log_id) AS pid
        FROM
            (
                SELECT
                    log_id,
                    IF((log_id - LAG(log_id) OVER (ORDER BY log_id)) = 1, 0, 1) AS delta
                FROM Logs
            ) AS t
    )
SELECT MIN(log_id) AS start_id, MAX(log_id) AS end_id
FROM T
GROUP BY pid;
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            log_id,
            log_id - ROW_NUMBER() OVER (ORDER BY log_id) AS pid
        FROM Logs
    )
SELECT MIN(log_id) AS start_id, MAX(log_id) AS end_id
FROM T
GROUP BY pid;
```

<!-- tabs:end -->

<!-- end -->
