---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3140.Consecutive%20Available%20Seats%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3140. 连续空余座位 II 🔒](https://leetcode.cn/problems/consecutive-available-seats-ii)

[English Version](/solution/3100-3199/3140.Consecutive%20Available%20Seats%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Cinema</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| seat_id     | int  |
| free        | bool |
+-------------+------+
seat_id 是这张表中的自增列。
这张表的每一行表示第 i 个作为是否空余。1 表示空余，而 0 表示被占用。
</pre>

<p>编写一个解决方案来找到电影院中 <strong>最长的空余座位</strong> 的 <strong>长度</strong>。</p>

<p>注意：</p>

<ul>
	<li>保证 <strong>最多有一个</strong> 最长连续序列。</li>
	<li>如果有 <strong>多个</strong> <strong>相同长度</strong> 的连续序列，将它们全部输出。</li>
</ul>

<p>返回结果表以&nbsp;<code>first_seat_id</code>&nbsp;<strong>升序排序</strong>。</p>

<p>结果表的格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>Cinema 表：</p>

<pre class="example-io">
+---------+------+
| seat_id | free |
+---------+------+
| 1       | 1    |
| 2       | 0    |
| 3       | 1    |
| 4       | 1    |
| 5       | 1    |
+---------+------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-----------------+----------------+-----------------------+
| first_seat_id   | last_seat_id   | consecutive_seats_len |
+-----------------+----------------+-----------------------+
| 3               | 5              | 3                     |
+-----------------+----------------+-----------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>可用座位的最长连续序列从座位 3 开始，到座位 5 结束，长度为 3。</li>
</ul>
输出表以 first_seat_id 升序排序。</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：使用窗口函数

我们首先找到所有空闲座位，然后将座位进行分组，分组的依据是座位的编号减去它的排名。这样一来，连续的空闲座位将会被分到同一组。接着我们找到每组中的最小座位编号、最大座位编号和连续座位的长度。最后我们找到连续座位长度最大的组，输出这个组的最小座位编号、最大座位编号和连续座位的长度。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            seat_id - (RANK() OVER (ORDER BY seat_id)) AS gid
        FROM Cinema
        WHERE free = 1
    ),
    P AS (
        SELECT
            MIN(seat_id) AS first_seat_id,
            MAX(seat_id) AS last_seat_id,
            COUNT(1) AS consecutive_seats_len,
            RANK() OVER (ORDER BY COUNT(1) DESC) AS rk
        FROM T
        GROUP BY gid
    )
SELECT first_seat_id, last_seat_id, consecutive_seats_len
FROM P
WHERE rk = 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
