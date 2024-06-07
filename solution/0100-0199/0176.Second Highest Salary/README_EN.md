---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0176.Second%20Highest%20Salary/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [176. Second Highest Salary](https://leetcode.com/problems/second-highest-salary)

[中文文档](/solution/0100-0199/0176.Second%20Highest%20Salary/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Employee</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
id is the primary key (column with unique values) for this table.
Each row of this table contains information about the salary of an employee.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find&nbsp;the second highest salary from the <code>Employee</code> table. If there is no second highest salary,&nbsp;return&nbsp;<code>null (return&nbsp;None in Pandas)</code>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
<strong>Output:</strong> 
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
<strong>Output:</strong> 
+---------------------+
| SecondHighestSalary |
+---------------------+
| null                |
+---------------------+
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Use Sub Query and LIMIT

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def second_highest_salary(employee: pd.DataFrame) -> pd.DataFrame:
    # Drop any duplicate salary values to avoid counting duplicates as separate salary ranks
    unique_salaries = employee["salary"].drop_duplicates()

    # Sort the unique salaries in descending order and get the second highest salary
    second_highest = (
        unique_salaries.nlargest(2).iloc[-1] if len(unique_salaries) >= 2 else None
    )

    # If the second highest salary doesn't exist (e.g., there are fewer than two unique salaries), return None
    if second_highest is None:
        return pd.DataFrame({"SecondHighestSalary": [None]})

    # Create a DataFrame with the second highest salary
    result_df = pd.DataFrame({"SecondHighestSalary": [second_highest]})

    return result_df
```

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    (
        SELECT DISTINCT salary
        FROM Employee
        ORDER BY salary DESC
        LIMIT 1, 1
    ) AS SecondHighestSalary;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Use `MAX()` function

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT MAX(salary) AS SecondHighestSalary
FROM Employee
WHERE salary < (SELECT MAX(salary) FROM Employee);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Use `IFNULL()` and window function

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH T AS (SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rk FROM Employee)
SELECT (SELECT DISTINCT salary FROM T WHERE rk = 2) AS SecondHighestSalary;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
