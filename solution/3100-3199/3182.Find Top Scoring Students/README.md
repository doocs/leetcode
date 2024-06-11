---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3182.Find%20Top%20Scoring%20Students/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3182. Find Top Scoring Students 🔒](https://leetcode.cn/problems/find-top-scoring-students)

[English Version](/solution/3100-3199/3182.Find%20Top%20Scoring%20Students/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <code>students</code></p>

<pre>
+-------------+----------+
| Column Name | Type     | 
+-------------+----------+
| student_id  | int      |
| name        | varchar  |
| major       | varchar  |
+-------------+----------+
student_id is the primary key (combination of columns with unique values) for this table.
Each row of this table contains the student ID, student name, and their major.
</pre>

<p>Table: <code>courses</code></p>

<pre>
+-------------+----------+
| Column Name | Type     | 
+-------------+----------+
| course_id   | int      |
| name        | varchar  |
| credits     | int      |
| major       | varchar  |
+-------------+----------+
course_id is the primary key (combination of columns with unique values) for this table.
Each row of this table contains the course ID, course name, the number of credits for the course, and the major it belongs to.
</pre>

<p>Table: <code>enrollments</code></p>

<pre>
+-------------+----------+
| Column Name | Type     | 
+-------------+----------+
| student_id  | int      |
| course_id   | int      |
| semester    | varchar  |
| grade       | varchar  |
+-------------+----------+
(student_id, course_id, semester) is the primary key (combination of columns with unique values) for this table.
Each row of this table contains the student ID, course ID, semester, and grade received.
</pre>

<p>Write a solution to find the students who have <strong>taken</strong> <strong>all courses</strong> offered in their <code>major</code> and have achieved a <strong>grade of A</strong> <strong>in all these courses</strong>.</p>

<p>Return <em>the result table ordered by</em> <code>student_id</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>students table:</p>

<pre class="example-io">
+------------+------------------+------------------+
| student_id | name             | major            |
+------------+------------------+------------------+
| 1          | Alice            | Computer Science |
| 2          | Bob              | Computer Science |
| 3          | Charlie          | Mathematics      |
| 4          | David            | Mathematics      |
+------------+------------------+------------------+
</pre>

<p>courses table:</p>

<pre class="example-io">
+-----------+-----------------+---------+------------------+
| course_id | name            | credits | major            |
+-----------+-----------------+---------+------------------+
| 101       | Algorithms      | 3       | Computer Science |
| 102       | Data Structures | 3       | Computer Science |
| 103       | Calculus        | 4       | Mathematics      |
| 104       | Linear Algebra  | 4       | Mathematics      |
+-----------+-----------------+---------+------------------+
</pre>

<p>enrollments table:</p>

<pre class="example-io">
+------------+-----------+----------+-------+
| student_id | course_id | semester | grade |
+------------+-----------+----------+-------+
| 1          | 101       | Fall 2023| A     |
| 1          | 102       | Fall 2023| A     |
| 2          | 101       | Fall 2023| B     |
| 2          | 102       | Fall 2023| A     |
| 3          | 103       | Fall 2023| A     |
| 3          | 104       | Fall 2023| A     |
| 4          | 103       | Fall 2023| A     |
| 4          | 104       | Fall 2023| B     |
+------------+-----------+----------+-------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+------------+
| student_id |
+------------+
| 1          |
| 3          |
+------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Alice (student_id 1) is a Computer Science major and has taken both &quot;Algorithms&quot; and &quot;Data Structures&quot;, receiving an &#39;A&#39; in both.</li>
	<li>Bob (student_id 2) is a Computer Science major but did not receive an &#39;A&#39; in all required courses.</li>
	<li>Charlie (student_id 3) is a Mathematics major and has taken both &quot;Calculus&quot; and &quot;Linear Algebra&quot;, receiving an &#39;A&#39; in both.</li>
	<li>David (student_id 4) is a Mathematics major but did not receive an &#39;A&#39; in all required courses.</li>
</ul>

<p><b>Note:</b> Output table is ordered by student_id in ascending order.</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：连接表 + 分组

我们可以将 `students` 表和 `courses` 按照 `major` 字段连接起来，然后再将 `enrollments` 表左连接到上述结果表中，最后按照 `student_id` 分组，筛选出满足条件的学生。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT student_id
FROM
    students
    JOIN courses USING (major)
    LEFT JOIN enrollments USING (student_id, course_id)
GROUP BY 1
HAVING SUM(grade = 'A') = COUNT(major)
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
