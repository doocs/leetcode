---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2298.Tasks%20Count%20in%20the%20Weekend/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2298. 周末任务计数 🔒](https://leetcode.cn/problems/tasks-count-in-the-weekend)

[English Version](/solution/2200-2299/2298.Tasks%20Count%20in%20the%20Weekend/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Tasks</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| task_id     | int  |
| assignee_id | int  |
| submit_date | date |
+-------------+------+
<code>task_id</code> 是该表的主键（具有唯一值的列）。
此表中的每一行都包含任务 ID、委托人 ID 和提交日期。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案来报告：</p>

<ul>
	<li>在周末 (周六，周日) 提交的任务的数量&nbsp;<code>weekend_cnt</code>，以及</li>
	<li>工作日内提交的任务数 <code>working_cnt</code>。</li>
</ul>

<p>按 <strong>任意顺序</strong> 返回结果表。<br />
返回结果格式如以下示例所示。</p>

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：WEEKDAY() 函数

<!-- thinking:start -->

> **思考**
>
> 统计周末提交与工作日提交的任务数。日期函数 $\textit{WEEKDAY}$ 把星期一到星期日映射为 $0$ 到 $6$，因此周六、周日对应 $5$ 和 $6$。
>
> 对全表做条件求和：$\textit{WEEKDAY}(\textit{submit\_date}) \in (5,6)$ 计入周末，其余计入工作日。一次聚合即可。

<!-- thinking:end -->

`WEEKDAY()` 函数返回日期的工作日编号，从 0 开始，0 表示星期一，1 表示星期二，以此类推，6 表示星期日。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    SUM(WEEKDAY(submit_date) IN (5, 6)) AS weekend_cnt,
    SUM(WEEKDAY(submit_date) NOT IN (5, 6)) AS working_cnt
FROM Tasks;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
