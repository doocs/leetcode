---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0181.Employees%20Earning%20More%20Than%20Their%20Managers/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [181. 超过经理收入的员工](https://leetcode.cn/problems/employees-earning-more-than-their-managers)

[English Version](/solution/0100-0199/0181.Employees%20Earning%20More%20Than%20Their%20Managers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Employee</code>&nbsp;</p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| salary      | int     |
| managerId   | int     |
+-------------+---------+
id 是该表的主键（具有唯一值的列）。
该表的每一行都表示雇员的ID、姓名、工资和经理的ID。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出收入比经理高的员工。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Employee 表:
+----+-------+--------+-----------+
| id | name  | salary | managerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | Null      |
| 4  | Max   | 90000  | Null      |
+----+-------+--------+-----------+
<strong>输出:</strong> 
+----------+
| Employee |
+----------+
| Joe      |
+----------+
<strong>解释:</strong> Joe 是唯一挣得比经理多的雇员。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def find_employees(employee: pd.DataFrame) -> pd.DataFrame:
    df = employee.merge(right=employee, how="left", left_on="managerId", right_on="id")
    emp = df[df["salary_x"] > df["salary_y"]]["name_x"]

    return pd.DataFrame({"Employee": emp})
```

#### MySQL

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    e1.name AS Employee
FROM
    Employee AS e1
    JOIN Employee AS e2 ON e1.managerId = e2.id
WHERE e1.salary > e2.salary;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
