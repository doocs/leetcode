# [176. 第二高的薪水](https://leetcode.cn/problems/second-highest-salary)

[English Version](/solution/0100-0199/0176.Second%20Highest%20Salary/README_EN.md)

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
id 是这个表的主键。
表的每一行包含员工的工资信息。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，获取并返回 <code>Employee</code>&nbsp;表中第二高的薪水 。如果不存在第二高的薪水，查询应该返回 <code>null</code> 。</p>

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

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

解法 1：使用 LIMIT 语句和子查询。

```sql
# Write your MySQL query statement below
SELECT
(
    SELECT DISTINCT Salary
    FROM Employee
    ORDER BY Salary DESC
    LIMIT 1 OFFSET 1
) AS SecondHighestSalary;
```

解法 2：使用 `MAX()` 函数，从小于 `MAX()` 的 Salary 中挑选最大值 `MAX()` 即可。

```sql
# Write your MySQL query statement below
SELECT MAX(Salary) AS SecondHighestSalary
FROM Employee
WHERE Salary < (
    SELECT MAX(Salary)
    FROM Employee
);
```

<!-- tabs:end -->
