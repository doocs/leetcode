---
comments: true
difficulty: 困难
tags:
    - 数据库
---

<!-- problem:start -->

# [3236. 首席执行官下属层级 🔒](https://leetcode.cn/problems/ceo-subordinate-hierarchy)

[English Version](/solution/3200-3299/3236.CEO%20Subordinate%20Hierarchy/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Employees</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| employee_id   | int     |
| employee_name | varchar |
| manager_id    | int     |
| salary        | int     |
+---------------+---------+
employee_id 是这张表的唯一标识符。
manager_id 是 employee_id 对应员工的经理。首席执行官的 manager_id 为 NULL。
</pre>

<p>编写一个解决方案来找到首席执行官的下属（<strong>直接</strong> 和&nbsp;<strong>非直接</strong>），以及他们在 <strong>等级制度中的级别</strong> 以及与首席执行官的 <strong>薪资差异</strong>。结果应该包含下面的列：</p>

<p>查询结果格式如下所示。</p>

<ul>
	<li><code>subordinate_id</code>：下属的 employee_id。</li>
	<li><code>subordinate_name</code>：下属的名字。</li>
	<li><code>hierarchy_level</code>：下属在等级制度中的级别（<code>1</code>&nbsp;表示直接下属，<code>2</code>&nbsp;表示 <b>他们的直接下属</b>，<strong>以此类推</strong>。）</li>
	<li><code>salary_difference</code>：下属与首席执行官的薪资差异。</li>
</ul>

<p>返回结果表以&nbsp;<code>hierarchy_level</code>&nbsp;<strong>升序排序</strong>，然后按&nbsp;<code>subordinate_id</code>&nbsp;<strong>升序排序</strong>。</p>

<p>查询格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p><code>Employees</code> 表：</p>

<pre class="example-io">
+-------------+----------------+------------+---------+
| employee_id | employee_name  | manager_id | salary  |
+-------------+----------------+------------+---------+
| 1           | Alice          | NULL       | 150000  |
| 2           | Bob            | 1          | 120000  |
| 3           | Charlie        | 1          | 110000  |
| 4           | David          | 2          | 105000  |
| 5           | Eve            | 2          | 100000  |
| 6           | Frank          | 3          | 95000   |
| 7           | Grace          | 3          | 98000   |
| 8           | Helen          | 5          | 90000   |
+-------------+----------------+------------+---------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+----------------+------------------+------------------+-------------------+
| subordinate_id | subordinate_name | hierarchy_level  | salary_difference |
+----------------+------------------+------------------+-------------------+
| 2              | Bob              | 1                | -30000            |
| 3              | Charlie          | 1                | -40000            |
| 4              | David            | 2                | -45000            |
| 5              | Eve              | 2                | -50000            |
| 6              | Frank            | 2                | -55000            |
| 7              | Grace            | 2                | -52000            |
| 8              | Helen            | 3                | -60000            |
+----------------+------------------+------------------+-------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>Bob 和 Charlie 是 Alice 的直接下属（首席执行官）因此，hierarchy_level 为 1。</li>
	<li>David 和 Eve 下属于 Bob，而&nbsp;Frank 和 Grace 下属于 Charlie，因此他们是二级下属（hierarchy_level 为 2）。</li>
	<li>Helen 下属于&nbsp;Eve，因此&nbsp;Helen 为三级下属（hierarchy_level 为 3）。</li>
	<li>薪资差异是相对于 Alice 的薪资 150000 计算的。</li>
	<li>结果先以 hierarchy_level 升序排序，然后以 subordinate_id 升序排序。</li>
</ul>

<p><strong>注意：</strong>输出表先以 hierarchy_level 升序排序，然后以 subordinate_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归 CTE + 连接

<!-- thinking:start -->

> **思考**
>
> 组织是以 CEO 为根的树，需要每名下属的层数及其与 CEO 的薪资差。层数未知时反复自连接次数不定。
>
> 递归 CTE 从 `manager_id IS NULL` 出发沿经理边向下，层数加一；再与 CEO 薪资交叉相减，去掉第 $0$ 层后按层与编号排序。

<!-- thinking:end -->

首先，我们使用递归 CTE 计算出每个员工的层级，其中 CEO 的层级为 0，将 `employee_id`、`employee_name`、`hierarchy_level`、`manager_id` 和 `salary` 保存到临时表 `T` 中。

然后，我们查询出 CEO 的薪资，将其保存到临时表 `P` 中。

最后，我们连接 `T` 和 `P` 表，计算出每个下属的薪资差异，并按照 `hierarchy_level` 和 `subordinate_id` 进行排序。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH RECURSIVE
    T AS (
        SELECT
            employee_id,
            employee_name,
            0 AS hierarchy_level,
            manager_id,
            salary
        FROM Employees
        WHERE manager_id IS NULL
        UNION ALL
        SELECT
            e.employee_id,
            e.employee_name,
            hierarchy_level + 1 AS hierarchy_level,
            e.manager_id,
            e.salary
        FROM
            T t
            JOIN Employees e ON t.employee_id = e.manager_id
    ),
    P AS (
        SELECT salary
        FROM Employees
        WHERE manager_id IS NULL
    )
SELECT
    employee_id subordinate_id,
    employee_name subordinate_name,
    hierarchy_level,
    t.salary - p.salary salary_difference
FROM
    T t
    JOIN P p
WHERE hierarchy_level != 0
ORDER BY 3, 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
