---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1978.Employees%20Whose%20Manager%20Left%20the%20Company/README.md
tags:
    - 数据库
---

# [1978. 上级经理已离职的公司员工](https://leetcode.cn/problems/employees-whose-manager-left-the-company)

[English Version](/solution/1900-1999/1978.Employees%20Whose%20Manager%20Left%20the%20Company/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Employees</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| employee_id | int      |
| name        | varchar  |
| manager_id  | int      |
| salary      | int      |
+-------------+----------+
在 SQL 中，employee_id 是这个表的主键。
这个表包含了员工，他们的薪水和上级经理的id。
有一些员工没有上级经理（其 manager_id 是空值）。
</pre>

<p>&nbsp;</p>

<p>查找这些员工的id，他们的薪水严格少于<code>$30000</code>&nbsp;并且他们的上级经理已离职。当一个经理离开公司时，他们的信息需要从员工表中删除掉，但是表中的员工的<code>manager_id</code> &nbsp;这一列还是设置的离职经理的id&nbsp;。</p>

<p>返回的结果按照<code>employee_id&nbsp;</code>从小到大排序。</p>

<p>查询结果如下所示：</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
Employees table:
+-------------+-----------+------------+--------+
| employee_id | name      | manager_id | salary |
+-------------+-----------+------------+--------+
| 3           | Mila      | 9          | 60301  |
| 12          | Antonella | null       | 31000  |
| 13          | Emery     | null       | 67084  |
| 1           | Kalel     | 11         | 21241  |
| 9           | Mikaela   | null       | 50937  |
| 11          | Joziah    | 6          | 28485  |
+-------------+-----------+------------+--------+
<strong>输出：</strong>
+-------------+
| employee_id |
+-------------+
| 11          |
+-------------+

<strong>解释：</strong>
薪水少于 30000 美元的员工有 1 号(Kalel) 和 11号 (Joziah)。
Kalel 的上级经理是 11 号员工，他还在公司上班(他是 Joziah )。
Joziah 的上级经理是 6 号员工，他已经离职，因为员工表里面已经没有 6 号员工的信息了，它被删除了。
</pre>

## 解法

### 方法一：左连接

我们可以使用左连接，将员工表自身连接一次，然后筛选出薪水小于 30000 的员工，且有上级经理，但是上级经理已经离职的员工。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT e1.employee_id
FROM
    Employees AS e1
    LEFT JOIN Employees AS e2 ON e1.manager_id = e2.employee_id
WHERE e1.salary < 30000 AND e1.manager_id IS NOT NULL AND e2.employee_id IS NULL
ORDER BY 1;
```

<!-- tabs:end -->

### 方法二：子查询

我们也可以使用子查询，先找出所有已经离职的经理，然后再找出薪水小于 30000 的员工，且他们的上级经理不在已经离职的经理列表中。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT employee_id
FROM Employees
WHERE salary < 30000 AND manager_id NOT IN (SELECT employee_id FROM Employees)
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
