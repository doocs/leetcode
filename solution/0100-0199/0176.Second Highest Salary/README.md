# [176. 第二高的薪水](https://leetcode.cn/problems/second-highest-salary)

[English Version](/solution/0100-0199/0176.Second%20Highest%20Salary/README_EN.md)

<!-- tags:数据库 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<code>Employee</code> 表：

<div class="original__bRMd">
<div>
<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
在 SQL 中，id 是这个表的主键。
表的每一行包含员工的工资信息。
</pre>

<p>&nbsp;</p>

<p>查询并返回 <code>Employee</code>&nbsp;表中第二高的薪水 。如果不存在第二高的薪水，查询应该返回 <code>null(Pandas 则返回 None)</code> 。</p>

<p>查询结果如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Employee 表：
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
<strong>输出：</strong>
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>
Employee 表：
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
<strong>输出：</strong>
+---------------------+
| SecondHighestSalary |
+---------------------+
| null                |
+---------------------+
</pre>
</div>
</div>

## 解法

### 方法一：使用 LIMIT 语句和子查询

我们可以按照薪水降序排列，然后使用 `LIMIT` 语句来获取第二高的薪水，如果不存在第二高的薪水，那么就返回 `null`。

<!-- tabs:start -->

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

### 方法二：使用 MAX() 函数和子查询

我们也可以使用 `MAX()` 函数，从小于 `MAX()` 的薪水中挑选一个最大的薪水即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT MAX(salary) AS SecondHighestSalary
FROM Employee
WHERE salary < (SELECT MAX(salary) FROM Employee);
```

<!-- tabs:end -->

### 方法三：使用 DISTINCT 和窗口函数

我们还可以先通过 `DENSE_RANK()` 函数计算出每个员工的薪水排名，然后再筛选出排名为 $2$ 的员工薪水即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH T AS (SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rk FROM Employee)
SELECT (SELECT DISTINCT salary FROM T WHERE rk = 2) AS SecondHighestSalary;
```

<!-- tabs:end -->

<!-- end -->
