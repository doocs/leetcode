# [1350. 院系无效的学生](https://leetcode.cn/problems/students-with-invalid-departments)

[English Version](/solution/1300-1399/1350.Students%20With%20Invalid%20Departments/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>院系表: <code>Departments</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
id 是该表的主键
该表包含一所大学每个院系的 id 信息
</pre>

<p>&nbsp;</p>

<p>学生表: <code>Students</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
| department_id | int     |
+---------------+---------+
id 是该表的主键
该表包含一所大学每个学生的 id 和他/她就读的院系信息
</pre>

<p>&nbsp;</p>

<p>写一条 SQL 语句以查询那些所在院系不存在的学生的 id 和姓名</p>

<p>可以以 <strong>任何顺序</strong> 返回结果。</p>

<p>下面是返回结果格式的例子。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Departments 表:
+------+--------------------------+
| id   | name                     |
+------+--------------------------+
| 1    | Electrical Engineering   |
| 7    | Computer Engineering     |
| 13   | Bussiness Administration |
+------+--------------------------+
Students 表:
+------+----------+---------------+
| id   | name     | department_id |
+------+----------+---------------+
| 23   | Alice    | 1             |
| 1    | Bob      | 7             |
| 5    | Jennifer | 13            |
| 2    | John     | 14            |
| 4    | Jasmine  | 77            |
| 3    | Steve    | 74            |
| 6    | Luis     | 1             |
| 8    | Jonathan | 7             |
| 7    | Daiana   | 33            |
| 11   | Madelynn | 1             |
+------+----------+---------------+
<strong>输出：</strong>
+------+----------+
| id   | name     |
+------+----------+
| 2    | John     |
| 7    | Daiana   |
| 4    | Jasmine  |
| 3    | Steve    |
+------+----------+
<strong>解释：</strong>
John, Daiana, Steve 和 Jasmine 所在的院系分别是 14, 33, 74 和 77， 其中 14, 33, 74 和 77 并不存在于院系表</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

外连接查询。

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    s.id, s.name
FROM
    Students s
LEFT JOIN
    Departments d
ON
    s.department_id = d.id
WHERE
    d.id IS NULL;
```

<!-- tabs:end -->
