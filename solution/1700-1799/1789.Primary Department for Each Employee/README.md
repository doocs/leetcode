# [1789. 员工的直属部门](https://leetcode.cn/problems/primary-department-for-each-employee)

[English Version](/solution/1700-1799/1789.Primary%20Department%20for%20Each%20Employee/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Employee</code></p>

<pre>+---------------+---------+
| Column Name   |  Type   |
+---------------+---------+
| employee_id   | int     |
| department_id | int     |
| primary_flag  | varchar |
+---------------+---------+
这张表的主键为 employee_id, department_id
employee_id 是员工的ID
department_id 是部门的ID，表示员工与该部门有关系
primary_flag 是一个枚举类型，值分别为('Y', 'N'). 如果值为'Y',表示该部门是员工的直属部门。 如果值是'N',则否
</pre>

<p> </p>

<p>一个员工可以属于多个部门。</p>

<p>当一个员工加入<strong>超过一个部门</strong>的时候，他需要决定哪个部门是他的直属部门。</p>

<p>请注意，当员工只加入一个部门的时候，那这个部门将默认为他的直属部门，虽然表记录的值为<code>'N'</code>.</p>

<p>请编写一段SQL，查出员工所属的直属部门。</p>

<p>返回结果没有顺序要求。</p>

<p> </p>

<p>示例：</p>

<pre>Employee table:
+-------------+---------------+--------------+
| employee_id | department_id | primary_flag |
+-------------+---------------+--------------+
| 1           | 1             | N            |
| 2           | 1             | Y            |
| 2           | 2             | N            |
| 3           | 3             | N            |
| 4           | 2             | N            |
| 4           | 3             | Y            |
| 4           | 4             | N            |
+-------------+---------------+--------------+

Result table:
+-------------+---------------+
| employee_id | department_id |
+-------------+---------------+
| 1           | 1             |
| 2           | 1             |
| 3           | 3             |
| 4           | 3             |
+-------------+---------------+
- 员工1的直属部门是1
- 员工2的直属部门是1
- 员工3的直属部门是3
- 员工4的直属部门是3</pre>

<p> </p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
