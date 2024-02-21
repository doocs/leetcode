# [1731. 每位经理的下属员工数量](https://leetcode.cn/problems/the-number-of-employees-which-report-to-each-employee)

[English Version](/solution/1700-1799/1731.The%20Number%20of%20Employees%20Which%20Report%20to%20Each%20Employee/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Employees</code></p>

<pre>+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| employee_id | int      |
| name        | varchar  |
| reports_to  | int      |
| age         | int      |
+-------------+----------+
employee_id 是这个表的主键.
该表包含员工以及需要听取他们汇报的上级经理的ID的信息。 有些员工不需要向任何人汇报（reports_to 为空）。
</pre>

<p> </p>

<p>对于此问题，我们将至少有一个其他员工需要向他汇报的员工，视为一个经理。</p>

<p>编写SQL查询需要听取汇报的所有经理的ID、名称、直接向该经理汇报的员工人数，以及这些员工的平均年龄，其中该平均年龄需要四舍五入到最接近的整数。</p>

<p>返回的结果集需要按照 <code>employee_id </code>进行排序。</p>

<p>查询结果的格式如下：</p>

<p> </p>

<pre>Employees table:
+-------------+---------+------------+-----+
| employee_id | name    | reports_to | age |
+-------------+---------+------------+-----+
| 9           | Hercy   | null       | 43  |
| 6           | Alice   | 9          | 41  |
| 4           | Bob     | 9          | 36  |
| 2           | Winston | null       | 37  |
+-------------+---------+------------+-----+

Result table:
+-------------+-------+---------------+-------------+
| employee_id | name  | reports_count | average_age |
+-------------+-------+---------------+-------------+
| 9           | Hercy | 2             | 39          |
+-------------+-------+---------------+-------------+
Hercy 有两个需要向他汇报的员工, 他们是 Alice and Bob. 他们的平均年龄是 (41+36)/2 = 38.5, 四舍五入的结果是 39.
</pre>

## 解法

### 方法一：自连接 + 分组统计

我们可以通过自连接的方式，将每个员工的上级经理信息连接到每个员工的信息上，然后再通过分组统计的方式，统计每个经理的下属员工数量和平均年龄。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    e2.employee_id,
    e2.name,
    COUNT(1) AS reports_count,
    ROUND(AVG(e1.age)) AS average_age
FROM
    Employees AS e1
    JOIN Employees AS e2 ON e1.reports_to = e2.employee_id
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
