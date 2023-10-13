# [1270. 向公司 CEO 汇报工作的所有人](https://leetcode.cn/problems/all-people-report-to-the-given-manager)

[English Version](/solution/1200-1299/1270.All%20People%20Report%20to%20the%20Given%20Manager/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>员工表：<code>Employees</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| employee_id   | int     |
| employee_name | varchar |
| manager_id    | int     |
+---------------+---------+
employee_id 是这个表具有唯一值的列。
这个表中每一行中，employee_id 表示职工的 ID，employee_name 表示职工的名字，manager_id 表示该职工汇报工作的直线经理。
这个公司 CEO 是 employee_id = 1 的人。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出所有直接或间接向公司 CEO 汇报工作的职工的 <code>employee_id</code> 。</p>

<p>由于公司规模较小，经理之间的间接关系 <strong>不超过 3 个经理</strong> 。</p>

<p>可以以 <strong>任何顺序</strong> 返回无重复项的结果。</p>

<p>返回结果示例如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
<code>Employees </code>table:
+-------------+---------------+------------+
| employee_id | employee_name | manager_id |
+-------------+---------------+------------+
| 1           | Boss          | 1          |
| 3           | Alice         | 3          |
| 2           | Bob           | 1          |
| 4           | Daniel        | 2          |
| 7           | Luis          | 4          |
| 8           | Jhon          | 3          |
| 9           | Angela        | 8          |
| 77          | Robert        | 1          |
+-------------+---------------+------------+
<strong>输出：</strong>
+-------------+
| employee_id |
+-------------+
| 2           |
| 77          |
| 4           |
| 7           |
+-------------+
<strong>解释：</strong>
公司 CEO 的 employee_id 是 1.
employee_id 是 2 和 77 的职员直接汇报给公司 CEO。
employee_id 是 4 的职员间接汇报给公司 CEO 4 --&gt; 2 --&gt; 1 。
employee_id 是 7 的职员间接汇报给公司 CEO 7 --&gt; 4 --&gt; 2 --&gt; 1 。
employee_id 是 3, 8 ，9 的职员不会直接或间接的汇报给公司 CEO。 
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：两次连接**

我们可以通过两次连接来找到所有直接或间接向公司 CEO 汇报工作的职工的 `employee_id`。

具体地，我们首先通过一次连接，找到每个 `manager_id` 对应的上级经理的 `manager_id`，然后再通过一次连接，找到更上一级经理的 `manager_id`，最后，如果更上一级的 `manager_id` 为 $1$，且员工的 `employee_id` 不为 $1$，则说明该员工直接或间接向公司 CEO 汇报工作。

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT e1.employee_id
FROM
    Employees AS e1
    JOIN Employees AS e2 ON e1.manager_id = e2.employee_id
    JOIN Employees AS e3 ON e2.manager_id = e3.employee_id
WHERE e1.employee_id != 1 AND e3.manager_id = 1;
```

<!-- tabs:end -->
