---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3182.Find%20Top%20Scoring%20Students/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3182. 查找得分最高的学生 🔒](https://leetcode.cn/problems/find-top-scoring-students)

[English Version](/solution/3100-3199/3182.Find%20Top%20Scoring%20Students/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>students</code></p>

<pre>
+-------------+----------+
| Column Name | Type     | 
+-------------+----------+
| student_id  | int      |
| name        | varchar  |
| major       | varchar  |
+-------------+----------+
student_id 是这张表的主键（有不同值的列的组合）。
这张表的每一行包含学生 ID，学生姓名和他们的专业。
</pre>

<p>表格：<code>courses</code></p>

<pre>
+-------------+----------+
| Column Name | Type     | 
+-------------+----------+
| course_id   | int      |
| name        | varchar  |
| credits     | int      |
| major       | varchar  |
+-------------+----------+
course_id 是这张表的主键（有不同值的列的组合）。
这张表的每一行包含课程 ID，课程名，课程学分和所属专业。
</pre>

<p>表：<code>enrollments</code></p>

<pre>
+-------------+----------+
| Column Name | Type     | 
+-------------+----------+
| student_id  | int      |
| course_id   | int      |
| semester    | varchar  |
| grade       | varchar  |
+-------------+----------+
(student_id, course_id, semester) 是这张表的主键（有不同值的列的组合）。
这张表的每一行包含学生 ID，课程 ID，学期和获得的学分。
</pre>

<p>编写一个解决方案来找到参加过他们的&nbsp;<code>major</code>&nbsp;提供的 <strong>所有课程&nbsp;</strong>并在&nbsp;<strong>所有这些课程中取得等级 A</strong> 的人。</p>

<p>返回结果表以&nbsp;<code>student_id</code> <em><strong>升序&nbsp;</strong></em>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><b>输入：</b></p>

<p>students 表：</p>

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

<p>courses 表：</p>

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

<p>enrollments 表：</p>

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

<p><strong>输出：</strong></p>

<pre class="example-io">
+------------+
| student_id |
+------------+
| 1          |
| 3          |
+------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>Alice (student_id 1) 是计算机科学专业并且修了 “Algorithms” 和 “Data Structures” 课程，都获得了 ‘A’。</li>
	<li>Bob (student_id 2) 是计算机科学专业但没有在全部必修课程中获得 ‘A’。</li>
	<li>Charlie (student_id 3) 是数学专业并且修了 “Calculus”&nbsp;和 “Linear Algebra”&nbsp;课程，都获得了 ‘A’。</li>
	<li>David (student_id 4) 是数学专业但没有在全部必修课程中获得 'A'。</li>
</ul>

<p><b>注意：</b>输出表以&nbsp;student_id 升序排序。</p>
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
