---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3262.Find%20Overlapping%20Shifts/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3262. Find Overlapping Shifts 🔒](https://leetcode.cn/problems/find-overlapping-shifts)

[English Version](/solution/3200-3299/3262.Find%20Overlapping%20Shifts/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <code>EmployeeShifts</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| employee_id      | int     |
| start_time       | time    |
| end_time         | time    |
+------------------+---------+
(employee_id, start_time) is the unique key for this table.
This table contains information about the shifts worked by employees, including the start and end times on a specific date.
</pre>

<p>Write a solution to count the number of <strong>overlapping shifts</strong> for each employee. Two shifts are considered overlapping if one shift&rsquo;s <code>end_time</code> is <strong>later than</strong> another shift&rsquo;s <code>start_time</code>.</p>

<p><em>Return the result table ordered by</em> <code>employee_id</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p><code>EmployeeShifts</code> table:</p>

<pre class="example-io">
+-------------+------------+----------+
| employee_id | start_time | end_time |
+-------------+------------+----------+
| 1           | 08:00:00   | 12:00:00 |
| 1           | 11:00:00   | 15:00:00 |
| 1           | 14:00:00   | 18:00:00 |
| 2           | 09:00:00   | 17:00:00 |
| 2           | 16:00:00   | 20:00:00 |
| 3           | 10:00:00   | 12:00:00 |
| 3           | 13:00:00   | 15:00:00 |
| 3           | 16:00:00   | 18:00:00 |
| 4           | 08:00:00   | 10:00:00 |
| 4           | 09:00:00   | 11:00:00 |
+-------------+------------+----------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+--------------------+
| employee_id | overlapping_shifts |
+-------------+--------------------+
| 1           | 2                  |
| 2           | 1                  |
| 4           | 1                  |
+-------------+--------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Employee 1 has 3 shifts:
	<ul>
		<li>08:00:00 to 12:00:00</li>
		<li>11:00:00 to 15:00:00</li>
		<li>14:00:00 to 18:00:00</li>
	</ul>
	The first shift overlaps with the second, and the second overlaps with the third, resulting in 2 overlapping shifts.</li>
	<li>Employee 2 has 2 shifts:
	<ul>
		<li>09:00:00 to 17:00:00</li>
		<li>16:00:00 to 20:00:00</li>
	</ul>
	These shifts overlap with each other, resulting in 1 overlapping shift.</li>
	<li>Employee 3 has 3 shifts:
	<ul>
		<li>10:00:00 to 12:00:00</li>
		<li>13:00:00 to 15:00:00</li>
		<li>16:00:00 to 18:00:00</li>
	</ul>
	None of these shifts overlap, so Employee 3 is not included in the output.</li>
	<li>Employee 4 has 2 shifts:
	<ul>
		<li>08:00:00 to 10:00:00</li>
		<li>09:00:00 to 11:00:00</li>
	</ul>
	These shifts overlap with each other, resulting in 1 overlapping shift.</li>
</ul>

<p>The output shows the employee_id and the count of overlapping shifts for each employee who has at least one overlapping shift, ordered by employee_id in ascending order.</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：自连接 + 分组计数

我们首先使用自连接，将 `EmployeeShifts` 表连接自身。通过连接条件，确保只比较同一个员工的班次，并且检查班次之间是否存在重叠。

1. `t1.start_time < t2.end_time`：确保第一个班次的开始时间早于第二个班次的结束时间。
1. `t1.end_time > t2.start_time`：确保第一个班次的结束时间晚于第二个班次的开始时间。
1. `t1.start_time != t2.start_time`：避免班次与自身比较。

接下来，我们对数据按照 `employee_id` 进行分组，统计每个员工的重叠班次数量。这里我们将重叠班次数量除以 2，因为我们在自连接时，每个重叠的班次都会被计算两次。

最后，我们筛选出重叠班次数量大于 0 的员工，并按照 `employee_id` 进行升序排序。

<!-- tabs:start -->

#### MySQL

```sql
SELECT
    t1.employee_id,
    COUNT(*) / 2 AS overlapping_shifts
FROM
    EmployeeShifts t1
    JOIN EmployeeShifts t2
        ON t1.employee_id = t2.employee_id
        AND t1.start_time < t2.end_time
        AND t1.end_time > t2.start_time
        AND t1.start_time != t2.start_time
GROUP BY 1
HAVING overlapping_shifts > 0
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def find_overlapping_shifts(employee_shifts: pd.DataFrame) -> pd.DataFrame:
    merged = employee_shifts.merge(
        employee_shifts, on="employee_id", suffixes=("_1", "_2")
    )
    overlap = merged[
        (merged["start_time_1"] < merged["end_time_2"])
        & (merged["end_time_1"] > merged["start_time_2"])
        & (merged["start_time_1"] != merged["start_time_2"])
    ]
    overlap_counts = (
        overlap.groupby("employee_id").size().reset_index(name="overlapping_shifts")
    )
    overlap_counts["overlapping_shifts"] = overlap_counts["overlapping_shifts"] // 2
    result = (
        overlap_counts[overlap_counts["overlapping_shifts"] > 0]
        .sort_values("employee_id")
        .reset_index(drop=True)
    )
    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
