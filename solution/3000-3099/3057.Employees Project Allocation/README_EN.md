# [3057. Employees Project Allocation](https://leetcode.com/problems/employees-project-allocation)

[中文文档](/solution/3000-3099/3057.Employees%20Project%20Allocation/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Project</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| project_id  | int     |
| employee_id | int     |
| workload    | int     |
+-------------+---------+
employee_id is the primary key (column with unique values) of this table.
employee_id is a foreign key (reference column) to <code>Employee</code> table.
Each row of this table indicates that the employee with employee_id is working on the project with project_id and the workload of the project.
</pre>

<p>Table: <code>Employees</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| employee_id      | int     |
| name             | varchar |
| team             | varchar |
+------------------+---------+
employee_id is the primary key (column with unique values) of this table.
Each row of this table contains information about one employee.
</pre>

<p>Write a solution to find the <strong>employees</strong> who are allocated to projects with a <strong>workload that exceeds the average</strong> workload of all employees for <strong>their respective teams</strong></p>

<p>Return t<em>he result table ordered by</em> <code>employee_id</code>, <code>project_id</code> <em>in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Project table:
+-------------+-------------+----------+
| project_id  | employee_id | workload |
+-------------+-------------+----------+
| 1           | 1           |  45      |
| 1           | 2           |  90      | 
| 2           | 3           |  12      |
| 2           | 4           |  68      |
+-------------+-------------+----------+
Employees table:
+-------------+--------+------+
| employee_id | name   | team |
+-------------+--------+------+
| 1           | Khaled | A    |
| 2           | Ali    | B    |
| 3           | John   | B    |
| 4           | Doe    | A    |
+-------------+--------+------+
<strong>Output:</strong> 
+-------------+------------+---------------+------------------+
| employee_id | project_id | employee_name | project_workload |
+-------------+------------+---------------+------------------+  
| 2           | 1          | Ali           | 90               | 
| 4           | 2          | Doe           | 68               | 
+-------------+------------+---------------+------------------+
<strong>Explanation:</strong> 
- Employee with ID 1 has a project workload of 45 and belongs to Team A, where the average workload is 56.50. Since his project workload does not exceed the team&#39;s average workload, he will be excluded.
- Employee with ID 2 has a project workload of 90 and belongs to Team B, where the average workload is 51.00. Since his project workload does exceed the team&#39;s average workload, he will be included.
- Employee with ID 3 has a project workload of 12 and belongs to Team B, where the average workload is 51.00. Since his project workload does not exceed the team&#39;s average workload, he will be excluded.
- Employee with ID 4 has a project workload of 68 and belongs to Team A, where the average workload is 56.50. Since his project workload does exceed the team&#39;s average workload, he will be included.
Result table orderd by employee_id, project_id in ascending order.
</pre>

## Solutions

### Solution 1: Grouping Statistics + Equi-Join

First, we join the `Project` table and the `Employees` table based on `employee_id`, then group by `team` to calculate the average workload of each team, and record it in the temporary table `T`.

Then, we join the `Project` table and the `Employees` table again, and also join the `T` table, to find employees whose workload is greater than the average workload of the team. Finally, we sort by `employee_id` and `project_id`.

<!-- tabs:start -->

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

<!-- end -->
