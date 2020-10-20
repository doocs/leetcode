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

```
select (select distinct Salary from Employee order by Salary desc limit 1 offset 1) as
SecondHighestSalary;
```

<!-- tabs:end -->
