---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3057.Employees%20Project%20Allocation/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3057. 员工项目分配 🔒](https://leetcode.cn/problems/employees-project-allocation)

[English Version](/solution/3000-3099/3057.Employees%20Project%20Allocation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Project</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| project_id  | int     |
| employee_id | int     |
| workload    | int     |
+-------------+---------+
employee_id 是这张表的主键（有不同值的列）。
employee_id 是 Employee 表的外键（引用列）。
这张表的每一行表示 employee_id 所指的员工正在 project_id 所指的项目上工作，以及项目的工作量。
</pre>

<p>表：<code>Employees</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| employee_id      | int     |
| name             | varchar |
| team             | varchar |
+------------------+---------+
employee_id 是这张表的主键（有不同值的列）。
这张表的每一行包含一个员工的信息。
</pre>

<p>编写一个解决方案，找出分配给项目的工作量 <strong>超过各自团队</strong> 所有员工 <strong>平均工作量</strong> 的 <strong>员工</strong>。</p>

<p>返回结果表，以&nbsp;<code>employee_id</code>，<code>project_id</code>&nbsp;<strong>升序</strong> 排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
Project 表：
+-------------+-------------+----------+
| project_id  | employee_id | workload |
+-------------+-------------+----------+
| 1           | 1           |  45      |
| 1           | 2           |  90      | 
| 2           | 3           |  12      |
| 2           | 4           |  68      |
+-------------+-------------+----------+
Employees 表：
+-------------+--------+------+
| employee_id | name   | team |
+-------------+--------+------+
| 1           | Khaled | A    |
| 2           | Ali    | B    |
| 3           | John   | B    |
| 4           | Doe    | A    |
+-------------+--------+------+
<strong>输出：</strong> 
+-------------+------------+---------------+------------------+
| employee_id | project_id | employee_name | project_workload |
+-------------+------------+---------------+------------------+  
| 2           | 1          | Ali           | 90               | 
| 4           | 2          | Doe           | 68               | 
+-------------+------------+---------------+------------------+
<strong>解释：</strong> 
- ID 为 1 的员工项目工作量为 45 并属于 Team A，其中平均工作量为 56.50。因为这个项目工作量没有超过小组的平均工作量，他将被排除。
- ID 为 2 的员工项目工作量为 90 并属于 Team B，其中平均工作量为 51.00。因为这个项目工作量超过小组的平均工作量，他将包含在结果中。
- ID 为 3 的员工项目工作量为 12 并属于 Team B，其中平均工作量为 51.00。因为这个项目工作量没有超过小组的平均工作量，他将被排除。
- ID 为 4 的员工项目工作量为 68 并属于 Team A，其中平均工作量为 56.50。因为这个项目工作量超过小组的平均工作量，他将包含在结果中。
结果表以 employee_id，project_id 升序排序。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计 + 等值连接

我们先根据 `employee_id` 连接 `Project` 表和 `Employees` 表，然后再根据 `team` 分组统计每个团队的平均工作量，记录在临时表 `T` 中。

然后，我们再次连接 `Project` 表和 `Employees` 表，同时连接 `T` 表，找出工作量大于团队平均工作量的员工，并且按照 `employee_id` 和 `project_id` 排序。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT team, AVG(workload) AS avg_workload
        FROM
            Project
            JOIN Employees USING (employee_id)
        GROUP BY 1
    )
SELECT
    employee_id,
    project_id,
    name AS employee_name,
    workload AS project_workload
FROM
    Project
    JOIN Employees USING (employee_id)
    JOIN T USING (team)
WHERE workload > avg_workload
ORDER BY 1, 2;
```

#### Python3

```python
import pandas as pd


def employees_with_above_avg_workload(
    project: pd.DataFrame, employees: pd.DataFrame
) -> pd.DataFrame:
    merged_df = pd.merge(project, employees, on="employee_id")
    avg_workload_per_team = merged_df.groupby("team")["workload"].mean().reset_index()
    merged_df = pd.merge(
        merged_df, avg_workload_per_team, on="team", suffixes=("", "_avg")
    )
    ans = merged_df[merged_df["workload"] > merged_df["workload_avg"]]
    ans = ans[["employee_id", "project_id", "name", "workload"]]
    ans = ans.rename(columns={"name": "employee_name", "workload": "project_workload"})
    return ans.sort_values(by=["employee_id", "project_id"])
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
