# [1767. Find the Subtasks That Did Not Execute 🔒](https://leetcode.com/problems/find-the-subtasks-that-did-not-execute)

[中文文档](/solution/1700-1799/1767.Find%20the%20Subtasks%20That%20Did%20Not%20Execute/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Tasks</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| task_id        | int     |
| subtasks_count | int     |
+----------------+---------+
task_id is the column with unique values for this table.
Each row in this table indicates that task_id was divided into subtasks_count subtasks labeled from 1 to subtasks_count.
It is guaranteed that 2 &lt;= subtasks_count &lt;= 20.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Executed</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| task_id       | int     |
| subtask_id    | int     |
+---------------+---------+
(task_id, subtask_id) is the combination of columns with unique values for this table.
Each row in this table indicates that for the task task_id, the subtask with ID subtask_id was executed successfully.
It is <strong>guaranteed</strong> that subtask_id &lt;= subtasks_count for each task_id.</pre>

<p>&nbsp;</p>

<p>Write a solution&nbsp;to report the IDs of the missing subtasks for each <code>task_id</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Tasks table:
+---------+----------------+
| task_id | subtasks_count |
+---------+----------------+
| 1       | 3              |
| 2       | 2              |
| 3       | 4              |
+---------+----------------+
Executed table:
+---------+------------+
| task_id | subtask_id |
+---------+------------+
| 1       | 2          |
| 3       | 1          |
| 3       | 2          |
| 3       | 3          |
| 3       | 4          |
+---------+------------+
<strong>Output:</strong> 
+---------+------------+
| task_id | subtask_id |
+---------+------------+
| 1       | 1          |
| 1       | 3          |
| 2       | 1          |
| 2       | 2          |
+---------+------------+
<strong>Explanation:</strong> 
Task 1 was divided into 3 subtasks (1, 2, 3). Only subtask 2 was executed successfully, so we include (1, 1) and (1, 3) in the answer.
Task 2 was divided into 2 subtasks (1, 2). No subtask was executed successfully, so we include (2, 1) and (2, 2) in the answer.
Task 3 was divided into 4 subtasks (1, 2, 3, 4). All of the subtasks were executed successfully.
</pre>

## Solutions

### Solution 1: Recursive Table Generation + Left Join

We can generate a table recursively that contains all pairs of (parent task, child task), and then use a left join to find the pairs that have not been executed.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH RECURSIVE
    T(task_id, subtask_id) AS (
        SELECT
            task_id,
            subtasks_count
        FROM Tasks
        UNION ALL
        SELECT
            task_id,
            subtask_id - 1
        FROM t
        WHERE subtask_id > 1
    )
SELECT
    T.*
FROM
    T
    LEFT JOIN Executed USING (task_id, subtask_id)
WHERE Executed.subtask_id IS NULL;
```

<!-- tabs:end -->

<!-- end -->
