---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3338.Second%20Highest%20Salary%20II/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3338. Second Highest Salary II 🔒](https://leetcode.com/problems/second-highest-salary-ii)

[中文文档](/solution/3300-3399/3338.Second%20Highest%20Salary%20II/README.md)

## Description

<!-- description:start -->

<p>Table: <code>employees</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| emp_id           | int     |
| salary           | int     |
| dept             | varchar |
+------------------+---------+
emp_id is the unique key for this table.
Each row of this table contains information about an employee including their ID, salary, and department.
</pre>

<p>Write a solution to find the employees who earn the <strong>second-highest salary</strong> in each department. If <strong>multiple employees have the second-highest salary</strong>, <strong>include</strong> <strong>all employees</strong> with <strong>that salary</strong>.</p>

<p>Return <em>the result table</em> <em>ordered by</em> <code>emp_id</code> <em>in</em> <em><strong>ascending</strong></em> <em>order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>employees table:</p>

<pre class="example-io">
+--------+--------+-----------+
| emp_id | salary | dept      |
+--------+--------+-----------+
| 1      | 70000  | Sales     |
| 2      | 80000  | Sales     |
| 3      | 80000  | Sales     |
| 4      | 90000  | Sales     |
| 5      | 55000  | IT        |
| 6      | 65000  | IT        |
| 7      | 65000  | IT        |
| 8      | 50000  | Marketing |
| 9      | 55000  | Marketing |
| 10     | 55000  | HR        |
+--------+--------+-----------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+--------+-----------+
| emp_id | dept      |
+--------+-----------+
| 2      | Sales     |
| 3      | Sales     |
| 5      | IT        |
| 8      | Marketing |
+--------+-----------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Sales Department</strong>:

    <ul>
    	<li>Highest salary is 90000 (emp_id: 4)</li>
    	<li>Second-highest salary is 80000 (emp_id: 2, 3)</li>
    	<li>Both employees with salary 80000 are included</li>
    </ul>
    </li>
    <li><strong>IT Department</strong>:
    <ul>
    	<li>Highest salary is 65000 (emp_id: 6, 7)</li>
    	<li>Second-highest salary is 55000 (emp_id: 5)</li>
    	<li>Only emp_id 5 is included as they have the second-highest salary</li>
    </ul>
    </li>
    <li><strong>Marketing Department</strong>:
    <ul>
    	<li>Highest salary is 55000 (emp_id: 9)</li>
    	<li>Second-highest salary is 50000 (emp_id: 8)</li>
    	<li>Employee 8&nbsp;is included</li>
    </ul>
    </li>
    <li><strong>HR Department</strong>:
    <ul>
    	<li>Only has one employee</li>
    	<li>Not included in the result as it has fewer than 2 employees</li>
    </ul>
    </li>

</ul>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Window Function

We can use the `DENSE_RANK()` window function to rank employees in each department by salary in descending order, and then filter out the employees with a rank of $2$.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            emp_id,
            dept,
            DENSE_RANK() OVER (
                PARTITION BY dept
                ORDER BY salary DESC
            ) rk
        FROM Employees
    )
SELECT emp_id, dept
FROM T
WHERE rk = 2
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def find_second_highest_salary(employees: pd.DataFrame) -> pd.DataFrame:
    employees["rk"] = employees.groupby("dept")["salary"].rank(
        method="dense", ascending=False
    )
    second_highest = employees[employees["rk"] == 2][["emp_id", "dept"]]
    return second_highest.sort_values(by="emp_id")
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
