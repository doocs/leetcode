---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3188.Find%20Top%20Scoring%20Students%20II/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3188. Find Top Scoring Students II 🔒](https://leetcode.com/problems/find-top-scoring-students-ii)

[中文文档](/solution/3100-3199/3188.Find%20Top%20Scoring%20Students%20II/README.md)

## Description

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
student_id is the primary key for this table. 
Each row contains the student ID, student name, and their major.
</pre>

<p>Table: <code>courses</code></p>

<pre>
+-------------+-------------------+
| Column Name | Type              |       
+-------------+-------------------+
| course_id   | int               |    
| name        | varchar           |      
| credits     | int               |           
| major       | varchar           |       
| mandatory   | enum              |      
+-------------+-------------------+
course_id is the primary key for this table. 
mandatory is an enum type of (&#39;Yes&#39;, &#39;No&#39;).
Each row contains the course ID, course name, credits, major it belongs to, and whether the course is mandatory.
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
| GPA         | decimal  | 
+-------------+----------+
(student_id, course_id, semester) is the primary key (combination of columns with unique values) for this table.
Each row contains the student ID, course ID, semester, and grade received.
</pre>

<p>Write a solution to find the students who meet the following criteria:</p>

<ul>
	<li>Have<strong> taken all mandatory courses</strong> and <strong>at least two</strong> elective courses offered in <strong>their major.</strong></li>
	<li>Achieved a grade of <strong>A</strong>&nbsp;in <strong>all mandatory courses</strong> and at least <strong>B</strong>&nbsp;in<strong> elective courses</strong>.</li>
	<li>Maintained an average <code>GPA</code> of at least&nbsp;<code>2.5</code> across all their courses (including those outside their major).</li>
</ul>

<p>Return <em>the result table ordered by</em> <code>student_id</code> <em>in <strong>ascending</strong> order</em>.</p>

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
 +-----------+-------------------+---------+------------------+----------+
 | course_id | name              | credits | major            | mandatory|
 +-----------+-------------------+---------+------------------+----------+
 | 101       | Algorithms        | 3       | Computer Science | yes      |
 | 102       | Data Structures   | 3       | Computer Science | yes      |
 | 103       | Calculus          | 4       | Mathematics      | yes      |
 | 104       | Linear Algebra    | 4       | Mathematics      | yes      |
 | 105       | Machine Learning  | 3       | Computer Science | no       |
 | 106       | Probability       | 3       | Mathematics      | no       |
 | 107       | Operating Systems | 3       | Computer Science | no       |
 | 108       | Statistics        | 3       | Mathematics      | no       |
 +-----------+-------------------+---------+------------------+----------+
 </pre>

<p>enrollments table:</p>

<pre class="example-io">
 +------------+-----------+-------------+-------+-----+
 | student_id | course_id | semester    | grade | GPA |
 +------------+-----------+-------------+-------+-----+
 | 1          | 101       | Fall 2023   | A     | 4.0 |
 | 1          | 102       | Spring 2023 | A     | 4.0 |
 | 1          | 105       | Spring 2023 | A     | 4.0 |
 | 1          | 107       | Fall 2023   | B     | 3.5 |
 | 2          | 101       | Fall 2023   | A     | 4.0 |
 | 2          | 102       | Spring 2023 | B     | 3.0 |
 | 3          | 103       | Fall 2023   | A     | 4.0 |
 | 3          | 104       | Spring 2023 | A     | 4.0 |
 | 3          | 106       | Spring 2023 | A     | 4.0 |
 | 3          | 108       | Fall 2023   | B     | 3.5 |
 | 4          | 103       | Fall 2023   | B     | 3.0 |
 | 4          | 104       | Spring 2023 | B     | 3.0 |
 +------------+-----------+-------------+-------+-----+
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
	<li>Alice (student_id 1) is a Computer Science major and has taken both Algorithms&nbsp;and Data Structures, receiving an A&nbsp;in both. She has also taken Machine Learning&nbsp;and Operating Systems&nbsp;as electives, receiving an A&nbsp;and B&nbsp;respectively.</li>
	<li>Bob (student_id 2) is a Computer Science major but did not receive an A&nbsp;in all required courses.</li>
	<li>Charlie (student_id 3) is a Mathematics major and has taken both Calculus&nbsp;and Linear Algebra, receiving an A&nbsp;in both. He has also taken Probability&nbsp;and Statistics&nbsp;as electives, receiving an A&nbsp;and B&nbsp;respectively.</li>
	<li>David (student_id 4) is a Mathematics major but did not receive an A&nbsp;in all required courses.</li>
</ul>

<p><strong>Note:</strong> Output table is ordered by student_id in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Joining + Grouping + Conditional Filtering

First, we filter out students with an average GPA greater than or equal to 2.5 and record them in table `T`.

Next, we join the `T` table with the `students` table based on `student_id`, then join with the `courses` table based on `major`, and finally perform a left join with the `enrollments` table based on `student_id` and `course_id`.

After that, we group by student ID, use the `HAVING` clause to filter out students who meet the conditions, and finally sort by student ID.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT student_id
        FROM enrollments
        GROUP BY 1
        HAVING AVG(GPA) >= 2.5
    )
SELECT student_id
FROM
    T
    JOIN students USING (student_id)
    JOIN courses USING (major)
    LEFT JOIN enrollments USING (student_id, course_id)
GROUP BY 1
HAVING
    SUM(mandatory = 'yes' AND grade = 'A') = SUM(mandatory = 'yes')
    AND SUM(mandatory = 'no' AND grade IS NOT NULL) = SUM(mandatory = 'no' AND grade IN ('A', 'B'))
    AND SUM(mandatory = 'no' AND grade IS NOT NULL) >= 2
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
