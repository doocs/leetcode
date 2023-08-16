# [580. Count Student Number in Departments](https://leetcode.com/problems/count-student-number-in-departments)

[中文文档](/solution/0500-0599/0580.Count%20Student%20Number%20in%20Departments/README.md)

## Description

<p>Table: <code>Student</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| student_id   | int     |
| student_name | varchar |
| gender       | varchar |
| dept_id      | int     |
+--------------+---------+
student_id is the primary key (column with unique values) for this table.
dept_id is a foreign key (reference column) to dept_id in the Department tables.
Each row of this table indicates the name of a student, their gender, and the id of their department.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Department</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| dept_id     | int     |
| dept_name   | varchar |
+-------------+---------+
dept_id is the primary key (column with unique values) for this table.
Each row of this table contains the id and the name of a department.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the respective department name and number of students majoring in each department for all departments in the <code>Department</code> table (even ones with no current students).</p>

<p>Return the result table <strong>ordered</strong> by <code>student_number</code> <strong>in descending order</strong>. In case of a tie, order them by <code>dept_name</code> <strong>alphabetically</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Student table:
+------------+--------------+--------+---------+
| student_id | student_name | gender | dept_id |
+------------+--------------+--------+---------+
| 1          | Jack         | M      | 1       |
| 2          | Jane         | F      | 1       |
| 3          | Mark         | M      | 2       |
+------------+--------------+--------+---------+
Department table:
+---------+-------------+
| dept_id | dept_name   |
+---------+-------------+
| 1       | Engineering |
| 2       | Science     |
| 3       | Law         |
+---------+-------------+
<strong>Output:</strong> 
+-------------+----------------+
| dept_name   | student_number |
+-------------+----------------+
| Engineering | 2              |
| Science     | 1              |
| Law         | 0              |
+-------------+----------------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT dept_id, count(1) AS cnt
        FROM Student
        GROUP BY dept_id
    )
SELECT dept_name, ifnull(cnt, 0) AS student_number
FROM
    S
    RIGHT JOIN Department USING (dept_id)
ORDER BY 2 DESC, 1;
```

<!-- tabs:end -->
