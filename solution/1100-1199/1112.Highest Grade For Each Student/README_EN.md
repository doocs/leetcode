# [1112. Highest Grade For Each Student](https://leetcode.com/problems/highest-grade-for-each-student)

[中文文档](/solution/1100-1199/1112.Highest%20Grade%20For%20Each%20Student/README.md)

## Description

<p>Table: <code>Enrollments</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| course_id     | int     |
| grade         | int     |
+---------------+---------+
(student_id, course_id) is the primary key of this table.

</pre>

<p>Write a SQL query to find the highest grade with its corresponding course for each student. In case of a tie, you should find the course with the smallest&nbsp;<code>course_id</code>. The output must be sorted by increasing <code>student_id</code>.</p>

<p>The query result format is in the following example:</p>

<pre>
Enrollments table:
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

Result table:
+------------+-------------------+
| student_id | course_id | grade |
+------------+-----------+-------+
| 1          | 2         | 99    |
| 2          | 2         | 95    |
| 3          | 3         | 82    |
+------------+-----------+-------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
