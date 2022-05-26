# [1767. 寻找没有被执行的任务对](https://leetcode.cn/problems/find-the-subtasks-that-did-not-execute)

[English Version](/solution/1700-1799/1767.Find%20the%20Subtasks%20That%20Did%20Not%20Execute/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Tasks</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| task_id        | int     |
| subtasks_count | int     |
+----------------+---------+
task_id 是这个表的主键。
task_id 表示的为主任务的id,每一个task_id被分为了多个子任务(subtasks)，subtasks_count表示为子任务的个数（n），它的值表示了子任务的索引从1到n。
本表保证2 &lt;=subtasks_count&lt;= 20。
</pre>

<p>&nbsp;</p>

<p>表： <code>Executed</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| task_id       | int     |
| subtask_id    | int     |
+---------------+---------+
(task_id, subtask_id) 是这个表的主键。
每一行表示标记为task_id的主任务与标记为subtask_id的子任务被成功执行。
本表 <strong>保证 </strong>，对于每一个task_id，subtask_id &lt;= subtasks_count。
</pre>

<p>&nbsp;</p>

<p>请试写一个SQL查询语句报告没有被执行的（主任务，子任务）对，即没有被执行的（task_id, subtask_id）。</p>

<p>以 <strong>任何顺序</strong> 返回即可。</p>

<p>查询结果格式如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：
</strong>Tasks 表:
+---------+----------------+
| task_id | subtasks_count |
+---------+----------------+
| 1       | 3              |
| 2       | 2              |
| 3       | 4              |
+---------+----------------+
Executed 表:
+---------+------------+
| task_id | subtask_id |
+---------+------------+
| 1       | 2          |
| 3       | 1          |
| 3       | 2          |
| 3       | 3          |
| 3       | 4          |
+---------+------------+
<strong>输出：</strong>
+---------+------------+
| task_id | subtask_id |
+---------+------------+
| 1       | 1          |
| 1       | 3          |
| 2       | 1          |
| 2       | 2          |
+---------+------------+
<strong>解释：</strong>
Task 1 被分成了 3 subtasks (1, 2, 3)。只有 subtask 2 被成功执行, 所以我们返回 (1, 1) 和 (1, 3) 这两个主任务子任务对。
Task 2 被分成了 2 subtasks (1, 2)。没有一个subtask被成功执行, 因此我们返回(2, 1)和(2, 2)。
Task 3 被分成了 4 subtasks (1, 2, 3, 4)。所有的subtask都被成功执行，因此对于Task 3,我们不返回任何值。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
