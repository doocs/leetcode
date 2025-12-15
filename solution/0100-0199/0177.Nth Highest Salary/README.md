---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0177.Nth%20Highest%20Salary/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [177. 第N高的薪水](https://leetcode.cn/problems/nth-highest-salary)

[English Version](/solution/0100-0199/0177.Nth%20Highest%20Salary/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表:&nbsp;<code>Employee</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
id 是该表的主键（列中的值互不相同）。
该表的每一行都包含有关员工工资的信息。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案查询&nbsp;<code>Employee</code> 表中第 <code>n</code> 高的&nbsp;<strong>不同</strong> 工资。如果少于&nbsp;<code>n</code> 个不同工资，查询结果应该为&nbsp;<code>null</code> 。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
n = 2
<strong>输出:</strong> 
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> 
Employee 表:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
n = 2
<strong>输出:</strong> 
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| null                   |
+------------------------+</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + LIMIT

我们可以先对 `salary` 进行降序排序，然后使用 `LIMIT` 语句获取第 $n$ 高的工资。

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def nth_highest_salary(employee: pd.DataFrame, N: int) -> pd.DataFrame:
    if N < 1:
        return pd.DataFrame({"getNthHighestSalary(" + str(N) + ")": [None]})
    unique_salaries = employee.salary.unique()
    if len(unique_salaries) < N:
        return pd.DataFrame([np.NaN], columns=[f"getNthHighestSalary({N})"])
    else:
        salary = sorted(unique_salaries, reverse=True)[N - 1]
        return pd.DataFrame([salary], columns=[f"getNthHighestSalary({N})"])
```

#### MySQL

```sql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N = N - 1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT (
          SELECT DISTINCT salary
          FROM Employee
          ORDER BY salary DESC
          LIMIT 1 OFFSET N
      )
  );
END
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
