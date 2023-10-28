# [570. 至少有 5 名直接下属的经理](https://leetcode.cn/problems/managers-with-at-least-5-direct-reports)

[English Version](/solution/0500-0599/0570.Managers%20with%20at%20Least%205%20Direct%20Reports/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表:&nbsp;<code>Employee</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| department  | varchar |
| managerId   | int     |
+-------------+---------+
在 SQL 中，id 是该表的主键列。
该表的每一行都表示雇员的名字、他们的部门和他们的经理的id。
如果managerId为空，则该员工没有经理。
没有员工会成为自己的管理者。
</pre>

<p>&nbsp;</p>

<p>查询<strong>至少有5名直接下属</strong>的经理<strong> </strong>。</p>

<p>以 <strong>任意顺序 </strong>返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Employee 表:
+-----+-------+------------+-----------+
| id  | name  | department | managerId |
+-----+-------+------------+-----------+
| 101 | John  | A          | None      |
| 102 | Dan   | A          | 101       |
| 103 | James | A          | 101       |
| 104 | Amy   | A          | 101       |
| 105 | Anne  | A          | 101       |
| 106 | Ron   | B          | 101       |
+-----+-------+------------+-----------+
<strong>输出:</strong> 
+------+
| name |
+------+
| John |
+------+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分组统计 + 连接**

我们可以先统计每个经理的直接下属人数，然后再连接 `Employee` 表，找出直接下属人数大于等于 $5$ 的经理。

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT name
FROM
    Employee
    JOIN (
        SELECT managerId AS id, COUNT(1) AS cnt
        FROM Employee
        GROUP BY 1
        HAVING cnt >= 5
    ) AS t
        USING (id);
```

### **Pandas**

```python
import pandas as pd


def find_managers(employee: pd.DataFrame) -> pd.DataFrame:
    # Group the employees by managerId and count the number of direct reports
    manager_report_count = (
        employee.groupby("managerId").size().reset_index(name="directReports")
    )

    # Filter managers with at least five direct reports
    result = manager_report_count[manager_report_count["directReports"] >= 5]

    # Merge with the Employee table to get the names of these managers
    result = result.merge(
        employee[["id", "name"]], left_on="managerId", right_on="id", how="inner"
    )

    # Select only the 'name' column and drop the 'id' and 'directReports' columns
    result = result[["name"]]

    return result

```

<!-- tabs:end -->
