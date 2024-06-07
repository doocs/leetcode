---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3156.Employee%20Task%20Duration%20and%20Concurrent%20Tasks/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3156. 员工任务持续时间和并发任务 🔒](https://leetcode.cn/problems/employee-task-duration-and-concurrent-tasks)

[English Version](/solution/3100-3199/3156.Employee%20Task%20Duration%20and%20Concurrent%20Tasks/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Tasks</code></p>

<pre>
+---------------+----------+
| Column Name   | Type     |
+---------------+----------+
| task_id       | int      |
| employee_id   | int      |
| start_time    | datetime |
| end_time      | datetime |
+---------------+----------+
(task_id, employee_id) 是这张表的主键。
这张表的每一行包含任务标识，员工标识和每个任务的开始和结束时间。
</pre>

<p>编写一个解决方案来查找 <strong>每个</strong> 员工的任务 <strong>总持续时间</strong> 以及员工在任何时间点处理的 <strong>最大并发任务数</strong>。总时长应该 <strong>舍入</strong> 到最近的 <strong>整小时</strong>。</p>

<p>返回结果表以&nbsp;<code>employee_id</code><strong> <em>升序</em></strong><em>&nbsp;排序。</em></p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>Tasks 表：</p>

<pre class="example-io">
+---------+-------------+---------------------+---------------------+
| task_id | employee_id | start_time          | end_time            |
+---------+-------------+---------------------+---------------------+
| 1       | 1001        | 2023-05-01 08:00:00 | 2023-05-01 09:00:00 |
| 2       | 1001        | 2023-05-01 08:30:00 | 2023-05-01 10:30:00 |
| 3       | 1001        | 2023-05-01 11:00:00 | 2023-05-01 12:00:00 |
| 7       | 1001        | 2023-05-01 13:00:00 | 2023-05-01 15:30:00 |
| 4       | 1002        | 2023-05-01 09:00:00 | 2023-05-01 10:00:00 |
| 5       | 1002        | 2023-05-01 09:30:00 | 2023-05-01 11:30:00 |
| 6       | 1003        | 2023-05-01 14:00:00 | 2023-05-01 16:00:00 |
+---------+-------------+---------------------+---------------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+------------------+----------------------+
| employee_id | total_task_hours | max_concurrent_tasks |
+-------------+------------------+----------------------+
| 1001        | 6                | 2                    |
| 1002        | 2                | 2                    |
| 1003        | 2                | 1                    |
+-------------+------------------+----------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于员工 ID 1001：
	<ul>
		<li>任务 1 和任务 2 从 08:30 到&nbsp;09:00 重叠（30 分钟）。</li>
		<li>任务 7 持续时间为 150 分钟（2 小时 30 分钟）。</li>
		<li>总工作小时：60（任务 1）+ 120（任务 2）+ 60（任务&nbsp;3）+ 150（任务 7）- 30（重叠）= 360 分钟 = 6 小时。</li>
		<li>最大并发任务：2 （重叠期间）。</li>
	</ul>
	</li>
	<li>对于员工 ID 1002：
	<ul>
		<li>任务 4 和任务 5 从 09:30 到&nbsp;10:00 重叠（30 分钟）。</li>
		<li>总工作时间：60 （任务&nbsp;4）+ 120（任务 5）- 30（重叠）= 150 分钟 = 2 小时 30 分钟。</li>
		<li>总工作小时：（舍入后）：2 小时。</li>
		<li>最大并发任务：2 （重叠期间）。</li>
	</ul>
	</li>
	<li>对于员工 ID 1003：
	<ul>
		<li>没有重叠的工作。</li>
		<li>总工作时间：120 分钟 = 2 小时。</li>
		<li>最大并发任务：1。</li>
	</ul>
	</li>
</ul>

<p><b>注意：</b>输出表以 employee_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT DISTINCT employee_id, start_time AS st
        FROM Tasks
        UNION DISTINCT
        SELECT DISTINCT employee_id, end_time AS st
        FROM Tasks
    ),
    P AS (
        SELECT
            *,
            LEAD(st) OVER (
                PARTITION BY employee_id
                ORDER BY st
            ) AS ed
        FROM T
    ),
    S AS (
        SELECT
            P.*,
            COUNT(1) AS concurrent_count
        FROM
            P
            INNER JOIN Tasks USING (employee_id)
        WHERE P.st >= Tasks.start_time AND P.ed <= Tasks.end_time
        GROUP BY 1, 2, 3
    )
SELECT
    employee_id,
    FLOOR(SUM(TIME_TO_SEC(TIMEDIFF(ed, st)) / 3600)) AS total_task_hours,
    MAX(concurrent_count) AS max_concurrent_tasks
FROM S
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
