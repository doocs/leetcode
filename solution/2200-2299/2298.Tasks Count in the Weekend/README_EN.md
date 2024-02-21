# [2298. Tasks Count in the Weekend](https://leetcode.com/problems/tasks-count-in-the-weekend)

[中文文档](/solution/2200-2299/2298.Tasks%20Count%20in%20the%20Weekend/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Tasks</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| task_id     | int  |
| assignee_id | int  |
| submit_date | date |
+-------------+------+
task_id is the primary key (column with unique values) for this table.
Each row in this table contains the ID of a task, the id of the assignee, and the submission date.
</pre>

<p>&nbsp;</p>

<p>Write a solution&nbsp;to report:</p>

<ul>
	<li>the number of tasks that were submitted during the weekend (Saturday, Sunday) as <code>weekend_cnt</code>, and</li>
	<li>the number of tasks that were submitted during the working days as <code>working_cnt</code>.</li>
</ul>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is shown in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Tasks table:
+---------+-------------+-------------+
| task_id | assignee_id | submit_date |
+---------+-------------+-------------+
| 1       | 1           | 2022-06-13  |
| 2       | 6           | 2022-06-14  |
| 3       | 6           | 2022-06-15  |
| 4       | 3           | 2022-06-18  |
| 5       | 5           | 2022-06-19  |
| 6       | 7           | 2022-06-19  |
+---------+-------------+-------------+
<strong>Output:</strong> 
+-------------+-------------+
| weekend_cnt | working_cnt |
+-------------+-------------+
| 3           | 3           |
+-------------+-------------+
<strong>Explanation:</strong> 
Task 1 was submitted on Monday.
Task 2 was submitted on Tuesday.
Task 3 was submitted on Wednesday.
Task 4 was submitted on Saturday.
Task 5 was submitted on Sunday.
Task 6 was submitted on Sunday.
3 tasks were submitted during the weekend.
3 tasks were submitted during the working days.
</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    SUM(WEEKDAY(submit_date) IN (5, 6)) AS weekend_cnt,
    SUM(WEEKDAY(submit_date) NOT IN (5, 6)) AS working_cnt
FROM Tasks;
```

<!-- tabs:end -->

<!-- end -->
