# [2298. Tasks Count in the Weekend](https://leetcode.cn/problems/tasks-count-in-the-weekend)

[English Version](/solution/2200-2299/2298.Tasks%20Count%20in%20the%20Weekend/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Tasks</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| task_id     | int  |
| assignee_id | int  |
| submit_date | date |
+-------------+------+
task_id is the primary key for this table.
Each row in this table contains the ID of a task, the id of the assignee, and the submission date.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report:</p>

<ul>
	<li>the number of the tasks that were submitted during the weekend (Saturday, Sunday) as <code>weekend_cnt</code>, and</li>
	<li>the number of the tasks that were submitted during the working days as <code>working_cnt</code>.</li>
</ul>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is shown in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
