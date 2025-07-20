---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3611.Find%20Overbooked%20Employees/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3611. Find Overbooked Employees](https://leetcode.com/problems/find-overbooked-employees)

[中文文档](/solution/3600-3699/3611.Find%20Overbooked%20Employees/README.md)

## Description

<!-- description:start -->

<p>Table: <code>employees</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| employee_id   | int     |
| employee_name | varchar |
| department    | varchar |
+---------------+---------+
employee_id is the unique identifier for this table.
Each row contains information about an employee and their department.
</pre>

<p>Table: <code>meetings</code></p>

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
meeting_id is the unique identifier for this table.
Each row represents a meeting attended by an employee. meeting_type can be &#39;Team&#39;, &#39;Client&#39;, or &#39;Training&#39;.
</pre>

<p>Write a solution to find employees who are <strong>meeting-heavy</strong> - employees who spend more than <code>50%</code> of their working time in meetings during any given week.</p>

<ul>
	<li>Assume a standard work week is <code>40</code><strong> hours</strong></li>
	<li>Calculate <strong>total meeting hours</strong> per employee <strong>per week</strong> (<strong>Monday to Sunday</strong>)</li>
	<li>An employee is meeting-heavy if their weekly meeting hours <code>&gt;</code> <code>20</code> hours (<code>50%</code> of <code>40</code> hours)</li>
	<li>Count how many weeks each employee was meeting-heavy</li>
	<li><strong>Only include</strong> employees who were meeting-heavy for <strong>at least </strong><code>2</code><strong> weeks</strong></li>
</ul>

<p>Return <em>the result table ordered by the number of meeting-heavy weeks in <strong>descending</strong> order, then by employee name in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>employees table:</p>

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

<p>meetings table:</p>

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

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+----------------+-------------+---------------------+
| employee_id | employee_name  | department  | meeting_heavy_weeks |
+-------------+----------------+-------------+---------------------+
| 1           | Alice Johnson  | Engineering | 2                   |
| 4           | David Wilson   | Engineering | 2                   |
+-------------+----------------+-------------+---------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Alice Johnson (employee_id = 1):</strong>

    <ul>
    	<li>Week of June 5-11 (2023-06-05 to 2023-06-11): 8.0 + 6.0 + 7.0 = 21.0 hours (&gt; 20 hours)</li>
    	<li>Week of June 12-18 (2023-06-12 to 2023-06-18): 12.0 + 9.0 = 21.0 hours (&gt; 20 hours)</li>
    	<li>Meeting-heavy for 2 weeks</li>
    </ul>
    </li>
    <li><strong>David Wilson (employee_id = 4):</strong>
    <ul>
    	<li>Week of June 5-11: 25.0 hours (&gt; 20 hours)</li>
    	<li>Week of June 19-25: 22.0 hours (&gt; 20 hours)</li>
    	<li>Meeting-heavy for 2 weeks</li>
    </ul>
    </li>
    <li><strong>Employees not included:</strong>
    <ul>
    	<li>Bob Smith (employee_id = 2): Week of June 5-11: 15.0 + 8.0 = 23.0 hours (&gt; 20), Week of June 12-18: 10.0 hours (&lt; 20). Only 1 meeting-heavy week</li>
    	<li>Carol Davis (employee_id = 3): Week of June 5-11: 4.0 + 3.0 = 7.0 hours (&lt; 20). No meeting-heavy weeks</li>
    	<li>Emma Brown (employee_id = 5): Week of June 5-11: 2.0 hours (&lt; 20). No meeting-heavy weeks</li>
    </ul>
    </li>

</ul>

<p>The result table is ordered by meeting_heavy_weeks in descending order, then by employee name in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Group Aggregation + Join Query

First, we group the data by `employee_id`, `year`, and `week` to calculate the total meeting hours for each employee in each week. Then, we filter out the weeks where the meeting hours exceed 20 and count the number of meeting-heavy weeks for each employee. Finally, we join the result with the employees table, filter out employees with at least 2 meeting-heavy weeks, and sort the results as required.

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
