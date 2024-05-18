---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3126.Server%20Utilization%20Time/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3126. 服务器利用时间 🔒](https://leetcode.cn/problems/server-utilization-time)

[English Version](/solution/3100-3199/3126.Server%20Utilization%20Time/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Servers</code></p>

<pre>
+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| server_id      | int      |
| status_time    | datetime |
| session_status | enum     |
+----------------+----------+
(server_id, status_time, session_status) 是这张表的主键（有不同值的列的组合）。
session_status 是 ('start', 'stop') 的 ENUM (category)。
这张表的每一行包含 server_id, status_time 和 session_status。
</pre>

<p>编写一个解决方案来查找服务器 <strong>运行</strong> 的 <strong>总时间</strong>。输出应四舍五入为最接近的 <strong>整天数</strong>。</p>

<p>以 <strong>任意</strong> 顺序返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>Servers 表：</p>

<pre class="example-io">
+-----------+---------------------+----------------+
| server_id | status_time         | session_status |
+-----------+---------------------+----------------+
| 3         | 2023-11-04 16:29:47 | start          |
| 3         | 2023-11-05 01:49:47 | stop           |
| 3         | 2023-11-25 01:37:08 | start          |
| 3         | 2023-11-25 03:50:08 | stop           |
| 1         | 2023-11-13 03:05:31 | start          |
| 1         | 2023-11-13 11:10:31 | stop           |
| 4         | 2023-11-29 15:11:17 | start          |
| 4         | 2023-11-29 15:42:17 | stop           |
| 4         | 2023-11-20 00:31:44 | start          |
| 4         | 2023-11-20 07:03:44 | stop           |
| 1         | 2023-11-20 00:27:11 | start          |
| 1         | 2023-11-20 01:41:11 | stop           |
| 3         | 2023-11-04 23:16:48 | start          |
| 3         | 2023-11-05 01:15:48 | stop           |
| 4         | 2023-11-30 15:09:18 | start          |
| 4         | 2023-11-30 20:48:18 | stop           |
| 4         | 2023-11-25 21:09:06 | start          |
| 4         | 2023-11-26 04:58:06 | stop           |
| 5         | 2023-11-16 19:42:22 | start          |
| 5         | 2023-11-16 21:08:22 | stop           |
+-----------+---------------------+----------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------------+
| total_uptime_days |
+-------------------+
| 1                 |
+-------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 server ID 3：
	<ul>
		<li>从 2023-11-04 16:29:47 到 2023-11-05 01:49:47: ~9.3 小时</li>
		<li>从 2023-11-25 01:37:08 到 2023-11-25 03:50:08: ~2.2 小时</li>
		<li>从 2023-11-04 23:16:48 到 2023-11-05 01:15:48: ~1.98 小时</li>
	</ul>
	server 3 共计：~13.48 小时</li>
	<li>对于 server ID 1：
	<ul>
		<li>从 2023-11-13 03:05:31 到 2023-11-13 11:10:31: ~8 小时</li>
		<li>从 2023-11-20 00:27:11 到 2023-11-20 01:41:11: ~1.23 小时</li>
	</ul>
	server 1 共计：~9.23 小时</li>
	<li>对于 server ID 4:
	<ul>
		<li>从 2023-11-29 15:11:17 到 2023-11-29 15:42:17: ~0.52 小时</li>
		<li>从 2023-11-20 00:31:44 到 2023-11-20 07:03:44: ~6.53 小时</li>
		<li>从 2023-11-30 15:09:18 到 2023-11-30 20:48:18: ~5.65 小时</li>
		<li>从 2023-11-25 21:09:06 到 2023-11-26 04:58:06: ~7.82 小时</li>
	</ul>
	server 4 共计：~20.52 小时</li>
	<li>对于 server ID 5:
	<ul>
		<li>从 2023-11-16 19:42:22 到 2023-11-16 21:08:22: ~1.43 小时</li>
	</ul>
	server 5 共计：~1.43 小时</li>
</ul>
所有服务器的累积运行时间总计约为 44.46 小时，相当于一整天加上一些额外的小时。然而，由于我们只考虑整天，因此最终输出四舍五入为 1 整天。</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：使用窗口函数

我们可以使用窗口函数 `LEAD` 来获取每个服务器的下一个状态的时间，那么两个状态之间的时间差就是服务器的一次运行时间。最后我们将所有服务器的运行时间相加，然后除以一天的秒数，就得到了服务器的总运行天数。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            session_status,
            status_time,
            LEAD(status_time) OVER (
                PARTITION BY server_id
                ORDER BY status_time
            ) AS next_status_time
        FROM Servers
    )
SELECT FLOOR(SUM(TIMESTAMPDIFF(SECOND, status_time, next_status_time)) / 86400) AS total_uptime_days
FROM T
WHERE session_status = 'start';
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
