# [580. 统计各专业学生人数](https://leetcode.cn/problems/count-student-number-in-departments)

[English Version](/solution/0500-0599/0580.Count%20Student%20Number%20in%20Departments/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表:&nbsp;<code>Student</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| student_id   | int     |
| student_name | varchar |
| gender       | varchar |
| dept_id      | int     |
+--------------+---------+
student_id 是该表的主键（具有唯一值的列）。
dept_id是Department表中dept_id的外键。
该表的每一行都表示学生的姓名、性别和所属系的id。
</pre>

<p>&nbsp;</p>

<p>表:&nbsp;<code>Department</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| dept_id     | int     |
| dept_name   | varchar |
+-------------+---------+
dept_id是该表的主键（具有唯一值的列）。
该表的每一行包含一个部门的id和名称。</pre>

<p>&nbsp;</p>

<p>编写解决方案，为&nbsp;<code>Department</code>&nbsp;表中的所有部门(甚至是没有当前学生的部门)报告各自的部门名称和每个部门的学生人数。</p>

<p>按 <code>student_number</code> <strong>降序&nbsp;</strong>返回结果表。如果是平局，则按 <code>dept_name</code> 的&nbsp; <strong>字母顺序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Student 表:
+------------+--------------+--------+---------+
| student_id | student_name | gender | dept_id |
+------------+--------------+--------+---------+
| 1          | Jack         | M      | 1       |
| 2          | Jane         | F      | 1       |
| 3          | Mark         | M      | 2       |
+------------+--------------+--------+---------+
Department 表:
+---------+-------------+
| dept_id | dept_name   |
+---------+-------------+
| 1       | Engineering |
| 2       | Science     |
| 3       | Law         |
+---------+-------------+
<strong>输出:</strong> 
+-------------+----------------+
| dept_name   | student_number |
+-------------+----------------+
| Engineering | 2              |
| Science     | 1              |
| Law         | 0              |
+-------------+----------------+</pre>

## 解法

### 方法一：左连接 + 分组统计

我们可以使用左连接，将 `Department` 表与 `Student` 表按照 `dept_id` 进行连接，然后按照 `dept_id` 分组统计学生人数，最后按照 `student_number` 降序、`dept_name` 升序排序即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT dept_name, COUNT(student_id) AS student_number
FROM
    Department
    LEFT JOIN Student USING (dept_id)
GROUP BY dept_id
ORDER BY 2 DESC, 1;
```

<!-- tabs:end -->

<!-- end -->
