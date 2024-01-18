# [1875. 将工资相同的雇员分组](https://leetcode.cn/problems/group-employees-of-the-same-salary)

[English Version](/solution/1800-1899/1875.Group%20Employees%20of%20the%20Same%20Salary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Employees</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| employee_id | int     |
| name        | varchar |
| salary      | int     |
+-------------+---------+
employee_id 是这张表具有唯一值的列.
这个表格的每一行包含雇员 ID, 姓名和工资信息.
</pre>

<p>&nbsp;</p>

<p>这家公司想要将&nbsp;<strong>工资相同&nbsp;</strong>的雇员划分到同一个组中。每个组需要满足如下要求：</p>

<ul>
	<li>每个组需要由&nbsp;<strong>至少两个&nbsp;</strong>雇员组成。</li>
	<li>同一个组中的所有雇员的&nbsp;<strong>工资相同</strong>。</li>
	<li>工资相同的所有雇员必须被分到同一个组中。</li>
	<li>如果某位雇员的工资是独一无二的，那么它&nbsp;<strong>不&nbsp;</strong>被分配到任何一个组中。</li>
	<li>组ID的设定基于这个组的工资相对于其他组的&nbsp;<strong>工资的排名</strong>，即工资&nbsp;<strong>最低&nbsp;</strong>的组满足&nbsp;<code>team_id = 1</code>&nbsp;。注意，排名时&nbsp;<strong>不需要考虑&nbsp;</strong>没有组的雇员的工资。</li>
</ul>

<p>编写一个解决方案来获取每一个被分配到组中的雇员的&nbsp;<code>team_id</code> 。</p>

<p>返回的结果表按照&nbsp;<code>team_id</code>&nbsp;<b>升序排列。</b>如果相同，则按照&nbsp;<code>employee_id</code>&nbsp;<strong>升序排列</strong>。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Employees 表:
+-------------+---------+--------+
| employee_id | name    | salary |
+-------------+---------+--------+
| 2           | Meir    | 3000   |
| 3           | Michael | 3000   |
| 7           | Addilyn | 7400   |
| 8           | Juan    | 6100   |
| 9           | Kannon  | 7400   |
+-------------+---------+--------+
<strong>输出：</strong>
+-------------+---------+--------+---------+
| employee_id | name    | salary | team_id |
+-------------+---------+--------+---------+
| 2           | Meir    | 3000   | 1       |
| 3           | Michael | 3000   | 1       |
| 7           | Addilyn | 7400   | 2       |
| 9           | Kannon  | 7400   | 2       |
+-------------+---------+--------+---------+
<strong>解释：</strong>
Meir (employee_id=2) 和 Michael (employee_id=3) 在同一个组中，因为他们的工资都是3000。
Addilyn (employee_id=7) 和 Kannon (employee_id=9) 在同一个组中，因为他们的工资都是7400。
Juan (employee_id=8) 不在任何一个组中，因为他的工资为6100，是独一无二的（即：没有人和他的工资相同）。
组ID按照如下方式分配（基于工资排名，较低的排在前面）:
- team_id=1: Meir 和 Michael, 工资是3000
- team_id=2: Addilyn 和 Kannon, 工资是7400
Juan的工资(6100)没有被计算在排名中，因为他不属于任何一个组。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT salary
        FROM Employees
        GROUP BY salary
        HAVING COUNT(1) > 1
    ),
    T AS (
        SELECT salary, ROW_NUMBER() OVER (ORDER BY salary) AS team_id
        FROM S
    )
SELECT e.*, t.team_id
FROM
    Employees AS e
    JOIN T AS t ON e.salary = t.salary
ORDER BY 4, 1;
```

<!-- tabs:end -->

<!-- end -->
