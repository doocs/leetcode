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
employee_id 是这个表的主键.
这个表格的每一行包含雇员ID(employee_id), 雇员姓名(name)和雇员工资(salary)信息.
</pre>

<p>&nbsp;</p>

<p>这家公司想要将<strong>工资相同</strong>的雇员划分到同一个队伍中。每个队伍需要满足如下要求：</p>

<ul>
	<li>每个队伍需要由<strong>至少两个</strong>雇员组成。</li>
	<li>同一个队伍中的所有雇员的<strong>工资相同</strong>。</li>
	<li>工资相同的所有雇员必须被分到同一个队伍中。</li>
	<li>如果某位雇员的工资是独一无二的，那么它<strong>不</strong>被分配到任何一个队伍中。</li>
	<li>队伍ID的设定基于这支队伍的工资相对于其他队伍的<strong>工资的排名</strong>，即工资<strong>最低</strong>的队伍满足&nbsp;<code>team_id = 1</code>&nbsp;。注意，排名时<strong>不需要考虑</strong>没有队伍的雇员的工资。</li>
</ul>

<p>编写一个 SQL&nbsp;查询来获取每一个被分配到队伍中的雇员的&nbsp;<code>team_id</code> 。</p>

<p>返回的结果表按照&nbsp;<code>team_id</code>&nbsp;<b>升序排列。</b>如果相同，则按照&nbsp;<code>employee_id</code>&nbsp;<strong>升序排列</strong>。</p>

<p>查询结果格式如下例。</p>

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
Meir (employee_id=2) 和 Michael (employee_id=3) 在同一个队伍中，因为他们的工资都是3000。
Addilyn (employee_id=7) 和 Kannon (employee_id=9) 在同一个队伍中，因为他们的工资都是7400。
Juan (employee_id=8) 不在任何一个队伍中，因为他的工资为6100，是独一无二的（即：没有人和他的工资相同）。
队伍ID按照如下方式分配（基于工资排名，较低的排在前面）:
- team_id=1: Meir 和 Michael, 工资是3000
- team_id=2: Addilyn 和 Kannon, 工资是7400
Juan的工资(6100)没有被计算在排名中，因为他不属于任何一个队伍。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
