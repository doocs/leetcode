---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3611.Find%20Overbooked%20Employees/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3611. 查找超预订员工](https://leetcode.cn/problems/find-overbooked-employees)

[English Version](/solution/3600-3699/3611.Find%20Overbooked%20Employees/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>employees</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| employee_id   | int     |
| employee_name | varchar |
| department    | varchar |
+---------------+---------+
employee_id 是这张表的唯一主键。
每一行包含一个员工和他们部门的信息。
</pre>

<p>表：<code>meetings</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| meeting_id    | int     |
| employee_id   | int     |
| meeting_date  | date    |
| meeting_type  | varchar |
| duration_hours| decimal |
+---------------+---------+
meeting_id 是这张表的唯一主键。
每一行表示一位员工参加的会议。meeting_type 可以是 'Team'，'Client' 或 'Training'。
</pre>

<p>编写一个解决方案来查找会议密集型的员工&nbsp;-&nbsp; 在任何给定周内，花费超过 <code>50%</code> 工作时间在会议上的员工。</p>

<ul>
	<li>假定一个标准工作周是&nbsp;<code>40</code><strong> 小时</strong></li>
	<li>计算每位员工 <strong>每周</strong>（<strong>周一至周日</strong>）的 <strong>总会议小时数</strong></li>
	<li>员工如果每周会议时间超过 <code>20</code> 小时（<code>40</code> 小时工作时间的 <code>50%</code>），则被视为会议密集型。</li>
	<li>统计每位员工有多少周是会议密集周</li>
	<li><strong>仅查找 至少</strong> <code>2</code> 周会议密集的员工</li>
</ul>

<p>返回结果表按会议密集周的数量降序排列，然后按员工姓名升序排列。结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>employees 表：</p>

<pre class="example-io">
+-------------+----------------+-------------+
| employee_id | employee_name  | department  |
+-------------+----------------+-------------+
| 1           | Alice Johnson  | Engineering |
| 2           | Bob Smith      | Marketing   |
| 3           | Carol Davis    | Sales       |
| 4           | David Wilson   | Engineering |
| 5           | Emma Brown     | HR          |
+-------------+----------------+-------------+
</pre>

<p>meetings 表：</p>

<pre class="example-io">
+------------+-------------+--------------+--------------+----------------+
| meeting_id | employee_id | meeting_date | meeting_type | duration_hours |
+------------+-------------+--------------+--------------+----------------+
| 1          | 1           | 2023-06-05   | Team         | 8.0            |
| 2          | 1           | 2023-06-06   | Client       | 6.0            |
| 3          | 1           | 2023-06-07   | Training     | 7.0            |
| 4          | 1           | 2023-06-12   | Team         | 12.0           |
| 5          | 1           | 2023-06-13   | Client       | 9.0            |
| 6          | 2           | 2023-06-05   | Team         | 15.0           |
| 7          | 2           | 2023-06-06   | Client       | 8.0            |
| 8          | 2           | 2023-06-12   | Training     | 10.0           |
| 9          | 3           | 2023-06-05   | Team         | 4.0            |
| 10         | 3           | 2023-06-06   | Client       | 3.0            |
| 11         | 4           | 2023-06-05   | Team         | 25.0           |
| 12         | 4           | 2023-06-19   | Client       | 22.0           |
| 13         | 5           | 2023-06-05   | Training     | 2.0            |
+------------+-------------+--------------+--------------+----------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+----------------+-------------+---------------------+
| employee_id | employee_name  | department  | meeting_heavy_weeks |
+-------------+----------------+-------------+---------------------+
| 1           | Alice Johnson  | Engineering | 2                   |
| 4           | David Wilson   | Engineering | 2                   |
+-------------+----------------+-------------+---------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>Alice Johnson (employee_id = 1):</strong>

    <ul>
    	<li>6 月 5 日至 11 日（2023-06-05 至 2023-06-11）：8.0 + 6.0 + 7.0 = 21.0 小时（&gt; 20 小时）</li>
    	<li>6 月 12&nbsp;日至 18&nbsp;日（2023-06-12 至 2023-06-18）: 12.0 + 9.0 = 21.0 小时（&gt; 20 小时）</li>
    	<li>2 周会议密集</li>
    </ul>
    </li>
    <li><strong>David Wilson (employee_id = 4):</strong>
    <ul>
    	<li>6 月 5 日至 11 日：25.0 小时（&gt; 20 小时）</li>
    	<li>6 月 19&nbsp;日至 25&nbsp;日：22.0 小时（&gt; 20 小时）</li>
    	<li>2 周会议密集</li>
    </ul>
    </li>
    <li><strong>未包含的员工：</strong>
    <ul>
    	<li>Bob Smith（employee_id = 2）：6 月 5 日至 11 日：15.0 + 8.0 = 23.0 小时（&gt; 20），6 月 12&nbsp;日至 18&nbsp;日：10.0 小时（&lt; 20）。只有 1 个会议密集周。</li>
    	<li>Carol Davis（employee_id = 3）：6 月 5 日至 11 日：4.0 + 3.0 = 7.0 小时（&lt; 20）。没有会议密集周。</li>
    	<li>Emma Brown（employee_id = 5）：6 月 5 日至 11 日：2.0 小时（&lt; 20）。没有会议密集周。</li>
    </ul>
    </li>

</ul>

<p>结果表按 meeting_heavy_weeks 降序排列，然后按员工姓名升序排列。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组聚合 + 连接查询

我们先将数据按照 `employee_id`、`year` 和 `week` 分组，计算每个员工每周的会议总时长。接着筛选出会议时长超过 20 小时的周数，并统计每个员工的会议密集周数。最后将结果与员工表连接，筛选出会议密集周数大于等于 2 的员工，并按要求排序。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    week_meeting_hours AS (
        SELECT
            employee_id,
            YEAR(meeting_date) AS year,
            WEEK(meeting_date, 1) AS week,
            SUM(duration_hours) hours
        FROM meetings
        GROUP BY 1, 2, 3
    ),
    intensive_weeks AS (
        SELECT
            employee_id,
            employee_name,
            department,
            count(1) AS meeting_heavy_weeks
        FROM
            week_meeting_hours
            JOIN employees USING (employee_id)
        WHERE hours >= 20
        GROUP BY 1
    )
SELECT employee_id, employee_name, department, meeting_heavy_weeks
FROM intensive_weeks
WHERE meeting_heavy_weeks >= 2
ORDER BY 4 DESC, 2;
```

#### Pandas

```python
import pandas as pd


def find_overbooked_employees(
    employees: pd.DataFrame, meetings: pd.DataFrame
) -> pd.DataFrame:
    meetings["meeting_date"] = pd.to_datetime(meetings["meeting_date"])
    meetings["year"] = meetings["meeting_date"].dt.isocalendar().year
    meetings["week"] = meetings["meeting_date"].dt.isocalendar().week

    week_meeting_hours = (
        meetings.groupby(["employee_id", "year", "week"], as_index=False)[
            "duration_hours"
        ]
        .sum()
        .rename(columns={"duration_hours": "hours"})
    )

    intensive_weeks = week_meeting_hours[week_meeting_hours["hours"] >= 20]

    intensive_count = (
        intensive_weeks.groupby("employee_id")
        .size()
        .reset_index(name="meeting_heavy_weeks")
    )

    result = intensive_count.merge(employees, on="employee_id")

    result = result[result["meeting_heavy_weeks"] >= 2]

    result = result.sort_values(
        ["meeting_heavy_weeks", "employee_name"], ascending=[False, True]
    )

    return result[
        ["employee_id", "employee_name", "department", "meeting_heavy_weeks"]
    ].reset_index(drop=True)
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
