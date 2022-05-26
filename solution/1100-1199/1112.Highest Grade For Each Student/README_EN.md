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

<p>&nbsp;</p>

<p>Write a SQL query to find the highest grade with its corresponding course for each student. In case of a tie, you should find the course with the smallest <code>course_id</code>.</p>

<p>Return the result table ordered by <code>student_id</code> in <strong>ascending order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
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
<strong>Output:</strong> 
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
