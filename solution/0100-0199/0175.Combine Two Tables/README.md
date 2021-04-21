# [175. 组合两个表](https://leetcode-cn.com/problems/combine-two-tables)

[English Version](/solution/0100-0199/0175.Combine%20Two%20Tables/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表1: <code>Person</code></p>

<pre>+-------------+---------+
| 列名         | 类型     |
+-------------+---------+
| PersonId    | int     |
| FirstName   | varchar |
| LastName    | varchar |
+-------------+---------+
PersonId 是上表主键
</pre>

<p>表2: <code>Address</code></p>

<pre>+-------------+---------+
| 列名         | 类型    |
+-------------+---------+
| AddressId   | int     |
| PersonId    | int     |
| City        | varchar |
| State       | varchar |
+-------------+---------+
AddressId 是上表主键
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供&nbsp;person 的以下信息：</p>

<p>&nbsp;</p>

<pre>FirstName, LastName, City, State
</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

左连接。

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT p.FirstName, p.LastName, a.City, a.State
FROM Person p
LEFT JOIN Address a
ON p.PersonId = a.PersonId;
```

<!-- tabs:end -->
