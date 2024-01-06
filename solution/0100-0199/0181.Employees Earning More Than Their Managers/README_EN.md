# [181. Employees Earning More Than Their Managers](https://leetcode.com/problems/employees-earning-more-than-their-managers)

[中文文档](/solution/0100-0199/0181.Employees%20Earning%20More%20Than%20Their%20Managers/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT Name AS Employee
FROM Employee AS Curr
WHERE
    Salary > (
        SELECT Salary
        FROM Employee
        WHERE Id = Curr.ManagerId
    );
```

```sql
# Write your MySQL query statement below
SELECT
    e1.name AS Employee
FROM
    Employee AS e1
    JOIN Employee AS e2 ON e1.managerId = e2.id
WHERE e1.salary > e2.salary;
```

### **Pandas**

```python
import pandas as pd


def find_employees(employee: pd.DataFrame) -> pd.DataFrame:
    df = employee.merge(right=employee, how="left", left_on="managerId", right_on="id")
    emp = df[df["salary_x"] > df["salary_y"]]["name_x"]

    return pd.DataFrame({"Employee": emp})
```

<!-- tabs:end -->
