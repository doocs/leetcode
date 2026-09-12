---
comments: true
difficulty: 困难
tags:
    - 数据库
---

<!-- problem:start -->

# [3268. 查找重叠的班次 II 🔒](https://leetcode.cn/problems/find-overlapping-shifts-ii)

[English Version](/solution/3200-3299/3268.Find%20Overlapping%20Shifts%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>EmployeeShifts</code></p>

<pre>
+------------------+----------+
| Column Name      | Type     |
+------------------+----------+
| employee_id      | int      |
| start_time       | datetime |
| end_time         | datetime |
+------------------+----------+
(employee_id, start_time) 是此表的唯一主键。
这张表包含员工的排班工作，包括特定日期的开始和结束时间。
</pre>

<p>编写一个解决方案来为每个员工分析重叠排班。如果两个排班在&nbsp;<strong>同一天</strong>&nbsp;且一个排班的&nbsp;<code>end_time</code>&nbsp;比另一个排班的&nbsp;<code>start_time</code>&nbsp;<strong>更晚&nbsp;</strong>则认为两个排班重叠。</p>

<p>对于&nbsp;<strong>每个员工</strong>，计算如下内容：</p>

<ol>
	<li>任何 <strong>给定时间</strong> 的 <strong>最多重叠</strong> 班次数。</li>
	<li>所有重叠班次的 <strong>总持续时间</strong>，以分钟为单位。</li>
</ol>

<p>返回结果表以&nbsp;<code>employee_id</code> <strong>升序&nbsp;</strong>排序。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p><code>EmployeeShifts</code> 表：</p>

<pre class="example-io">
+-------------+---------------------+---------------------+
| employee_id | start_time          | end_time            |
+-------------+---------------------+---------------------+
| 1           | 2023-10-01 09:00:00 | 2023-10-01 17:00:00 |
| 1           | 2023-10-01 15:00:00 | 2023-10-01 23:00:00 |
| 1           | 2023-10-01 16:00:00 | 2023-10-02 00:00:00 |
| 2           | 2023-10-01 09:00:00 | 2023-10-01 17:00:00 |
| 2           | 2023-10-01 11:00:00 | 2023-10-01 19:00:00 |
| 3           | 2023-10-01 09:00:00 | 2023-10-01 17:00:00 |
+-------------+---------------------+---------------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+---------------------------+------------------------+
| employee_id | max_overlapping_shifts    | total_overlap_duration |
+-------------+---------------------------+------------------------+
| 1           | 3                         | 600                    |
| 2           | 2                         | 360                    |
| 3           | 1                         | 0                      |
+-------------+---------------------------+------------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>员工 1 有 3 个排班：
	<ul>
		<li>2023-10-01 09:00:00 到 2023-10-01 17:00:00</li>
		<li>2023-10-01 15:00:00 到 2023-10-01 23:00:00</li>
		<li>2023-10-01 16:00:00 到 2023-10-02 00:00:00</li>
	</ul>
	最大重叠班次数量为 3 (from 16:00 to 17:00)。重叠班次的总持续时间为：第 1 个和第 2 个排班之间的 2 小时 (15:00-17:00) + 第 1 个和第 3 个排班之间的&nbsp;1 小时 (16:00-17:00) +&nbsp;第 2 个和第 3 个排班之间的 7 小时 (16:00-23:00)，总共：10 小时 = 600 分钟</li>
	<li>员工 2 有 2 个排班：
	<ul>
		<li>2023-10-01 09:00:00 到 2023-10-01 17:00:00</li>
		<li>2023-10-01 11:00:00 到 2023-10-01 19:00:00</li>
	</ul>
	最大重叠班次数量为 2。重叠班次的总持续时间为 6 小时&nbsp;(11:00-17:00) = 360 分钟。</li>
	<li>员工 3 只有 1 个排班，所以没有重叠。</li>
</ul>

<p>输出表包含 employee_id，同时重叠排班的最大数量,以及每位员工的重叠班次总持续时间（分钟），以&nbsp;employee_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：合并 + 连接

<!-- thinking:start -->

> **思考**
>
> 除重叠对数外还要最大同时班次数与总重叠分钟。自连接能得两两重叠时长，同时人数则要把时间轴上的进出事件切开。
>
> 把每名员工的起止时刻去重后用 `LEAD` 连成原子区间，再与原班次连接统计覆盖数；两两重叠时长另用自连接累加。最后按员工取最大并发与总时长。

<!-- thinking:end -->

我们可以将所有 `employee_id` 的 `start_time` 和 `end_time` 合并到一起，记录在 `T` 表中，然后使用 `LEAD` 函数计算出每个 `employee_id` 的下一个时间段，记录在 `P` 表中。

接着，我们可以通过 `P` 表和 `EmployeeShifts` 表进行连接，计算出每个 `employee_id` 的 `concurrent_count`，即同时存在的时间段数量，记录在 `S` 表中。

最后，我们可以通过 `EmployeeShifts` 表和自身进行连接，计算出每个 `employee_id` 的 `total_overlap_duration`，即总的重叠时间，记录在 `U` 表中。

最终，我们可以通过 `S` 表和 `U` 表进行连接，计算出每个 `employee_id` 的 `max_overlapping_shifts` 和 `total_overlap_duration`。

相似题目：

- [3156. 员工任务持续时间和并发任务 🔒](https://github.com/doocs/leetcode/blob/main/solution/3100-3199/3156.Employee%20Task%20Duration%20and%20Concurrent%20Tasks/README.md)
- [3262. 查找重叠的班次 🔒](https://github.com/doocs/leetcode/blob/main/solution/3200-3299/3262.Find%20Overlapping%20Shifts/README.md)

<!-- tabs:start -->

#### MySQL

```sql
WITH
    T AS (
        SELECT DISTINCT employee_id, start_time AS st
        FROM EmployeeShifts
        UNION DISTINCT
        SELECT DISTINCT employee_id, end_time AS st
        FROM EmployeeShifts
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
            INNER JOIN EmployeeShifts USING (employee_id)
        WHERE P.st >= EmployeeShifts.start_time AND P.ed <= EmployeeShifts.end_time
        GROUP BY 1, 2, 3
    ),
    U AS (
        SELECT
            t1.employee_id,
            SUM(
                TIMESTAMPDIFF(MINUTE, t2.start_time, LEAST(t1.end_time, t2.end_time))
            ) total_overlap_duration
        FROM
            EmployeeShifts t1
            JOIN EmployeeShifts t2
                ON t1.employee_id = t2.employee_id
                AND t1.start_time < t2.start_time
                AND t1.end_time > t2.start_time
        GROUP BY 1
    )
SELECT
    employee_id,
    MAX(concurrent_count) max_overlapping_shifts,
    IFNULL(AVG(total_overlap_duration), 0) total_overlap_duration
FROM
    S
    LEFT JOIN U USING (employee_id)
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
