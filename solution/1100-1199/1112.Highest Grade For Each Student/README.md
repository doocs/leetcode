# [1112. 每位学生的最高成绩](https://leetcode.cn/problems/highest-grade-for-each-student)

[English Version](/solution/1100-1199/1112.Highest%20Grade%20For%20Each%20Student/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Enrollments</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| course_id     | int     |
| grade         | int     |
+---------------+---------+
(student_id, course_id) 是该表的主键。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，查询每位学生获得的最高成绩和它所对应的科目，若科目成绩并列，取&nbsp;<code>course_id</code>&nbsp;最小的一门。查询结果需按&nbsp;<code>student_id</code>&nbsp;增序进行排序。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Enrollments 表：
+------------+-------------------+
| student_id | course_id | grade |
+------------+-----------+-------+
| 2          | 2         | 95    |
| 2          | 3         | 95    |
| 1          | 1         | 90    |
| 1          | 2         | 99    |
| 3          | 1         | 80    |
| 3          | 2         | 75    |
| 3          | 3         | 82    |
+------------+-----------+-------+
<strong>输出：</strong>
+------------+-------------------+
| student_id | course_id | grade |
+------------+-----------+-------+
| 1          | 2         | 99    |
| 2          | 2         | 95    |
| 3          | 3         | 82    |
+------------+-----------+-------+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT
  student_id,
  course_id,
  grade
FROM (SELECT
  *,
  RANK() OVER (PARTITION BY student_id ORDER BY grade DESC, course_id) rk
FROM Enrollments) a
WHERE a.rk = 1;
```

<!-- tabs:end -->
