---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0181.Employees%20Earning%20More%20Than%20Their%20Managers/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [181. Employees Earning More Than Their Managers](https://leetcode.com/problems/employees-earning-more-than-their-managers)

[中文文档](/solution/0100-0199/0181.Employees%20Earning%20More%20Than%20Their%20Managers/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Employee</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| salary      | int     |
| managerId   | int     |
+-------------+---------+
id is the primary key (column with unique values) for this table.
Each row of this table indicates the ID of an employee, their name, salary, and the ID of their manager.
</pre>

<p>&nbsp;</p>

<p>Write a solution&nbsp;to find the employees who earn more than their managers.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employee table:
+----+-------+--------+-----------+
| id | name  | salary | managerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | Null      |
| 4  | Max   | 90000  | Null      |
+----+-------+--------+-----------+
<strong>Output:</strong> 
+----------+
| Employee |
+----------+
| Joe      |
+----------+
<strong>Explanation:</strong> Joe is the only employee who earns more than his manager.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Self-Join + Conditional Filtering

We can find employees' salaries and their managers' salaries by self-joining the `Employee` table, then filter out employees whose salaries are higher than their managers' salaries.

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def find_employees(employee: pd.DataFrame) -> pd.DataFrame:
    merged = employee.merge(
        employee, left_on="managerId", right_on="id", suffixes=("", "_manager")
    )
    result = merged[merged["salary"] > merged["salary_manager"]][["name"]]
    result.columns = ["Employee"]
    return result
```

#### MySQL

```sql
# Write your MySQL query statement below
SELECT e1.name Employee
FROM
    Employee e1
    JOIN Employee e2 ON e1.managerId = e2.id
WHERE e1.salary > e2.salary;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
