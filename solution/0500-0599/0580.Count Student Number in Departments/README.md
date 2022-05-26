# [580. 统计各专业学生人数](https://leetcode.cn/problems/count-student-number-in-departments)

[English Version](/solution/0500-0599/0580.Count%20Student%20Number%20in%20Departments/README_EN.md)

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
Student_id是该表的主键。
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
Dept_id是该表的主键。
该表的每一行包含一个部门的id和名称。</pre>

<p>&nbsp;</p>

<p>编写一个SQL查询，为&nbsp;<code>Department</code>&nbsp;表中的所有部门(甚至是没有当前学生的部门)报告各自的部门名称和每个部门的学生人数。</p>

<p>按 <code>student_number</code> <strong>降序&nbsp;</strong>返回结果表。如果是平局，则按 <code>dept_name</code> 的&nbsp; <strong>字母顺序&nbsp;</strong>排序。</p>

<p>查询结果格式如下所示。</p>

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

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT
    department.dept_name, COUNT(student.dept_id) student_number
FROM
    Student
        RIGHT JOIN
    Department ON student.dept_id = department.dept_id
GROUP BY dept_name
ORDER BY student_number DESC , dept_name;
```

<!-- tabs:end -->
