# [2298. 周末任务计数](https://leetcode.cn/problems/tasks-count-in-the-weekend)

[English Version](/solution/2200-2299/2298.Tasks%20Count%20in%20the%20Weekend/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Tasks</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| task_id     | int  |
| assignee_id | int  |
| submit_date | date |
+-------------+------+
task_id 是此表的主键。
此表中的每一行都包含任务 ID、委托人 ID 和提交日期。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 来查询：</p>

<ul>
	<li>在周末 (周六，周日) 提交的任务的数量&nbsp;<code>weekend_cnt</code>，以及</li>
	<li>工作日内提交的任务数 <code>working_cnt</code>。</li>
</ul>

<p>按 <strong>任意顺序</strong> 返回结果表。<br />
查询结果格式如以下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Tasks 表:
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
<strong>输出:</strong> 
+-------------+-------------+
| weekend_cnt | working_cnt |
+-------------+-------------+
| 3           | 3           |
+-------------+-------------+
<strong>解释:</strong> 
Task 1 是在周一提交的。
Task 2 是在周二提交的。
Task 3 是在周三提交的。
Task 4 是在周六提交的。
Task 5 是在周日提交的。
Task 6 是在周日提交的。
3 个任务是在周末提交的。
3 个任务是在工作日提交的。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
