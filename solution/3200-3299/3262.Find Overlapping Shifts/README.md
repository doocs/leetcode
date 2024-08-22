---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3262.Find%20Overlapping%20Shifts/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3262. 查找重叠的班次 🔒](https://leetcode.cn/problems/find-overlapping-shifts)

[English Version](/solution/3200-3299/3262.Find%20Overlapping%20Shifts/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>EmployeeShifts</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| employee_id      | int     |
| start_time       | time    |
| end_time         | time    |
+------------------+---------+
(employee_id, start_time) 是此表的唯一主键。
这张表包含员工的排班工作，包括特定日期的开始和结束时间。
</pre>

<p>编写一个解决方案来为每个员工计算&nbsp;<strong>重叠排班</strong>&nbsp;的数量。如果一个排班的&nbsp;<code>end_time</code>&nbsp;比另一个排班的&nbsp;<code>start_time</code>&nbsp;<strong>更晚&nbsp;</strong>则认为两个排班重叠。</p>

<p>返回结果表以&nbsp;<code>employee_id</code> <strong>升序&nbsp;</strong>排序。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><b>示例：</b></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p><code>EmployeeShifts</code> 表：</p>

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

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+--------------------+
| employee_id | overlapping_shifts |
+-------------+--------------------+
| 1           | 2                  |
| 2           | 1                  |
| 4           | 1                  |
+-------------+--------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>员工 1 有 3 个排班：
	<ul>
		<li>08:00:00 到 12:00:00</li>
		<li>11:00:00 到 15:00:00</li>
		<li>14:00:00 到 18:00:00</li>
	</ul>
	第一个排班与第二个排班重叠，第二个排班与第三个排班重叠，因此有 2&nbsp;个重叠排班。</li>
	<li>员工 2&nbsp;有 2 个排班：
	<ul>
		<li>09:00:00 到 17:00:00</li>
		<li>16:00:00 到 20:00:00</li>
	</ul>
	这些排班彼此重叠，因此有 1 个重叠排班。</li>
	<li>员工 3 有 3 个排班：
	<ul>
		<li>10:00:00 到 12:00:00</li>
		<li>13:00:00 到 15:00:00</li>
		<li>16:00:00 到 18:00:00</li>
	</ul>
	这些排班没有重叠，所以员工 3 不包含在输出中。</li>
	<li>员工 4 有 2 个排班：
	<ul>
		<li>08:00:00 到 10:00:00</li>
		<li>09:00:00 到 11:00:00</li>
	</ul>
	这些排班彼此重叠，因此有 1 个重叠排班。</li>
</ul>

<p>输出展示了 employee_id 和至少有一个重叠排班的员工的重叠排班的数量，以 employee_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：自连接 + 分组计数

我们首先使用自连接，将 `EmployeeShifts` 表连接自身。通过连接条件，确保只比较同一个员工的班次，并且检查班次之间是否存在重叠。

1. `t1.start_time < t1.start_time`：确保第一个班次的开始时间早于第二个班次的结束时间。
1. `t1.end_time > t2.start_time`：确保第一个班次的结束时间晚于第二个班次的开始时间。

接下来，我们对数据按照 `employee_id` 进行分组，统计每个员工的重叠班次数量。

最后，我们筛选出重叠班次数量大于 $0$ 的员工，并按照 `employee_id` 进行升序排序。

<!-- tabs:start -->

#### MySQL

```sql
SELECT
    t1.employee_id,
    COUNT(*) AS overlapping_shifts
FROM
    EmployeeShifts t1
    JOIN EmployeeShifts t2
        ON t1.employee_id = t2.employee_id
        AND t1.start_time < t2.start_time
        AND t1.end_time > t2.start_time
GROUP BY 1
HAVING overlapping_shifts > 0
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def find_overlapping_shifts(employee_shifts: pd.DataFrame) -> pd.DataFrame:
    merged_shifts = employee_shifts.merge(
        employee_shifts, on="employee_id", suffixes=("_t1", "_t2")
    )
    overlapping_shifts = merged_shifts[
        (merged_shifts["start_time_t1"] < merged_shifts["start_time_t2"])
        & (merged_shifts["end_time_t1"] > merged_shifts["start_time_t2"])
    ]
    result = (
        overlapping_shifts.groupby("employee_id")
        .size()
        .reset_index(name="overlapping_shifts")
    )
    result = result[result["overlapping_shifts"] > 0]
    result = result.sort_values("employee_id").reset_index(drop=True)
    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
