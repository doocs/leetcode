# [176. 第二高的薪水](https://leetcode-cn.com/problems/second-highest-salary)

[English Version](/solution/0100-0199/0176.Second%20Highest%20Salary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个 SQL 查询，获取 <code>Employee</code>&nbsp;表中第二高的薪水（Salary）&nbsp;。</p>

<pre>+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
</pre>

<p>例如上述&nbsp;<code>Employee</code>&nbsp;表，SQL查询应该返回&nbsp;<code>200</code> 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 <code>null</code>。</p>

<pre>+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
</pre>


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
